package com.patika.notificationservice.service;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.NotificationDetailDTO;

public interface NotificationService {

    NotificationDetailDTO sendNotification(ApplicationDetailDTO applicationDetailDTO);
}
