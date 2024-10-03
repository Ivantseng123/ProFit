package com.ProFit.dao.eventsCRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsDAO extends JpaRepository<EventsBean, String> {

}