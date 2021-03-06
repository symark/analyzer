package pl.stock.data.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import pl.piomin.core.data.generic.GenericDaoImpl;
import pl.stock.data.dao.DailyQuoteRecordDao;
import pl.stock.data.entity.Company;
import pl.stock.data.entity.DailyQuoteRecord;

/**
 * Class with DAO operations for DailyQuoteRecord entity
 * @author Piotr Mińkowski
 *
 */
@Repository
public class DailyQuoteRecordDaoImpl extends GenericDaoImpl<Long, DailyQuoteRecord> implements DailyQuoteRecordDao {

	public DailyQuoteRecordDaoImpl() {
		super(DailyQuoteRecord.class);
	}

	public DailyQuoteRecord findLastByCompany(Company company) {
		return (DailyQuoteRecord) this
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select a from DailyQuoteRecord a left join fetch a.statistic c where a.id = (select max(id) from DailyQuoteRecord b where b.company = :company)")
				.setEntity("company", company).uniqueResult();
	}

	public int countByCompany(Company company) {
		return ((Long) this.getSessionFactory().getCurrentSession().createQuery("select count(id) from DailyQuoteRecord a where a.company = :company")
				.setEntity("company", company).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<DailyQuoteRecord> findByCompany(Company company, int maxCount) {
		final String query = "select r from DailyQuoteRecord r join fetch r.statistic where r.company = :company order by r.date desc";
		return this.getSessionFactory().getCurrentSession().createQuery(query).setEntity("company", company).setMaxResults(maxCount).list();
	}

	@SuppressWarnings("unchecked")
	public List<DailyQuoteRecord> findAllByCompany(Company company) {
		final String query = "select r from DailyQuoteRecord r left join fetch r.statistic where r.company = :company order by r.date desc";
		return this.getSessionFactory().getCurrentSession().createQuery(query).setEntity("company", company).list();
	}

	@SuppressWarnings("unchecked")
	public List<DailyQuoteRecord> findByCompanyOlderThan(Company company, Date maxDate, int maxCount) {
		final String query = "select r from DailyQuoteRecord r left join fetch r.statistic where r.company = :company and r.date <= :maxDate order by r.date desc";
		return this.getSessionFactory().getCurrentSession().createQuery(query).setEntity("company", company).setDate("maxDate", maxDate).setMaxResults(maxCount)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyQuoteRecord> findByDateAndIds(Date from, Date to, Integer[] companyIds) {
		return this.getSessionFactory().getCurrentSession()
				.createQuery(
						"select r from DailyQuoteRecord r join fetch r.statistic left join fetch r.company c where r.date between :from and :to and c.id in (:ids) order by c.id asc, r.date desc")
				.setDate("from", from).setDate("to", to).setParameterList("ids", companyIds).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DailyQuoteRecord> findByDatePeriod(Date from, Date to) {
		return this.getSessionFactory().getCurrentSession()
				.createQuery("select r from DailyQuoteRecord r join fetch r.statistic s join fetch r.company c where r.date between :from and :to order by c.id asc, r.date desc")
				.setDate("from", from).setDate("to", to).list();
	}
}
