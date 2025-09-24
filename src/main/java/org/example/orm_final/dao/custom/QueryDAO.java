package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
 public List<Object[]> getStudentsWithCous();
}
