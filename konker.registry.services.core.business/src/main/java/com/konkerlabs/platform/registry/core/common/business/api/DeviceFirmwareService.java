package com.konkerlabs.platform.registry.core.common.business.api;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.Application;
import com.konkerlabs.platform.registry.core.common.model.DeviceFirmware;
import com.konkerlabs.platform.registry.core.common.model.DeviceModel;
import com.konkerlabs.platform.registry.core.common.model.Tenant;

import java.util.List;

public interface DeviceFirmwareService {

	enum Validations {

		FIRMWARE_ALREADY_REGISTERED("service.device_firmware.already_registered");

		public String getCode() {
			return code;
		}

		private String code;

		Validations(String code) {
			this.code = code;
		}

	}

    ServiceResponse<DeviceFirmware> save(Tenant tenant, Application application, DeviceFirmware deviceFirmware);

    ServiceResponse<List<DeviceFirmware>> listByDeviceModel(Tenant tenant, Application application, DeviceModel deviceModel);

}