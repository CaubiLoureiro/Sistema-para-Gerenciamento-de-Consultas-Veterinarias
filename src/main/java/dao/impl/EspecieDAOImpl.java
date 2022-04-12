package dao.impl;

import org.springframework.stereotype.Repository;

import dao.EspecieDAO;
import modelo.Especie;

@Repository
public abstract class EspecieDAOImpl extends JPADaoGenerico<Especie, Long> implements EspecieDAO {
	public EspecieDAOImpl() {
		super(Especie.class);
	}
}
