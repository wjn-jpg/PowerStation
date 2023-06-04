package com.ntdq.power_station.build.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends Serializable> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {
}
