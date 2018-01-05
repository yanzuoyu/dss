package com.enerbos.cloud.dss.service.repository.jpa;

import com.enerbos.cloud.dss.service.domain.UploadLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadLogRepository extends JpaRepository<UploadLog, String> {
}
