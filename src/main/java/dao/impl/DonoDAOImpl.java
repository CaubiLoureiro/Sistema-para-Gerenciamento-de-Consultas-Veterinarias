package dao.impl;

import org.springframework.stereotype.Repository;

import dao.DonoDAO;
import modelo.Dono;

@Repository
public abstract class DonoDAOImpl extends JPADaoGenerico<Dono, Long> implements DonoDAO {
	public DonoDAOImpl() {
		super(Dono.class);
	}
}
