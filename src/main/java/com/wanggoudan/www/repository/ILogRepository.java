package com.wanggoudan.www.repository;

import com.wanggoudan.www.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILogRepository extends MongoRepository<Log, String> {
}
