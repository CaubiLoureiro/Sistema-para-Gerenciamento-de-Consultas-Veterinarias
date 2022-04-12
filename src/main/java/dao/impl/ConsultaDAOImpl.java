package dao.impl;

import org.springframework.stereotype.Repository;

import dao.ConsultaDAO;
import modelo.Consulta;

@Repository
public abstract class ConsultaDAOImpl extends JPADaoGenerico<Consulta, Long> implements ConsultaDAO {
	public ConsultaDAOImpl() {
		super(Consulta.class);
	}
}
