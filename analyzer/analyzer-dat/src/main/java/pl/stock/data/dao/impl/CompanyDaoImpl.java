package pl.stock.data.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.piomin.core.data.generic.GenericDaoImpl;
import pl.stock.data.dao.CompanyDao;
import pl.stock.data.entity.Company;

/**
 * Class with DAO operations for Company entity
 * @author Piotr Mińkowski
 *
 */
@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Integer, Company> implements CompanyDao {

	public CompanyDaoImpl() {
	    super(Company.class);
	}
	
	@SuppressWarnings("unchecked")
	public Company findBySymbol(final String symbol) {
		List<Object> objects = this.getSessionFactory().getCurrentSession().createQuery("from Company a where a.symbol = :symbol").setString("symbol", symbol).list();
		if (objects.size() > 0) {
			return (Company) objects.get(0);
		} else {
			return null;
		}
	}
}
