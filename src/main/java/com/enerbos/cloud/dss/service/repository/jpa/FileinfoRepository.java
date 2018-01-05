package com.enerbos.cloud.dss.service.repository.jpa;

import com.enerbos.cloud.dss.service.domain.Fileinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileinfoRepository  extends JpaRepository<Fileinfo, String> {
}
