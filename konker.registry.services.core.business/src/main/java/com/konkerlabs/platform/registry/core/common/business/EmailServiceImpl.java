package com.konkerlabs.platform.registry.core.common.business;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.api.ServiceResponseBuilder;
import com.konkerlabs.platform.registry.core.common.business.api.EmailService;
import com.konkerlabs.platform.registry.core.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public ServiceResponse<ServiceResponse.Status> send(String sender,
														List<User> recipients,
														List<User> recipientsCopied,
														String subject,
														String templateName,
														Map<String, Object> templateParam,
														Locale locale) {
		try {
			if (!Optional.ofNullable(sender).isPresent() || sender.isEmpty()) {
				return ServiceResponseBuilder.<ServiceResponse.Status>error().withMessage(Validations.SENDER_NULL.getCode()).build();
			}
			if (!Optional.ofNullable(recipients).isPresent() || recipients.isEmpty()) {
				return ServiceResponseBuilder.<ServiceResponse.Status>error().withMessage(Validations.RECEIVERS_NULL.getCode()).build();
			}
			if (!Optional.ofNullable(subject).isPresent() || subject.isEmpty()) {
				return ServiceResponseBuilder.<ServiceResponse.Status>error().withMessage(Validations.SUBJECT_EMPTY.getCode()).build();
			}
			if (!Optional.ofNullable(templateName).isPresent() || templateName.isEmpty()) {
				return ServiceResponseBuilder.<ServiceResponse.Status>error().withMessage(Validations.BODY_EMPTY.getCode()).build();
			}
			
			final Context ctx = new Context(locale);
			ctx.setVariables(templateParam);
			
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			message.setSubject(subject);
			message.setFrom(sender);
			message.setTo(recipients.stream().map(r -> r.getEmail()).collect(Collectors.toList()).toArray(new String[0]));
			
			if (Optional.ofNullable(recipientsCopied).isPresent()) {
				message.setCc(recipientsCopied.stream().map(r -> r.getEmail()).collect(Collectors.toList()).toArray(new String[0]));
			}
			
			final String htmlContent = this.templateEngine.process(templateName, ctx);
			message.setText(htmlContent, true);
			
			this.mailSender.send(mimeMessage);
			
			return ServiceResponseBuilder.<ServiceResponse.Status>ok().build();
			
		} catch (MessagingException e) {
			return ServiceResponseBuilder.<ServiceResponse.Status>error()
				.withMessage(e.getMessage())
				.build();
		}
	}
	
}
