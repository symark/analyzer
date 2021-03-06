package pl.stock.data.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.piomin.core.data.generic.GenericServiceImpl;
import pl.stock.data.dao.DailyQuoteRecordDao;
import pl.stock.data.entity.Company;
import pl.stock.data.entity.DailyQuoteRecord;
import pl.stock.data.service.DailyQuoteRecordService;

@Service
@Transactional
public class DailyQuoteRecordServiceImpl extends GenericServiceImpl<Long, DailyQuoteRecord> implements DailyQuoteRecordService {

	@Autowired
	public void setDailyQuoteRecordDao(DailyQuoteRecordDao dailyQuoteRecordDao) {
		this.dao = dailyQuoteRecordDao;
	}

	@Override
	public DailyQuoteRecord findLastByCompany(Company company) {
		return ((DailyQuoteRecordDao) this.dao).findLastByCompany(company);
	}

	@Override
	public int countByCompany(Company company) {
		return ((DailyQuoteRecordDao) this.dao).countByCompany(company);
	}

	@Override
	public List<DailyQuoteRecord> findByCompany(Company company, int maxCount) {
		return ((DailyQuoteRecordDao) this.dao).findByCompany(company, maxCount);
	}

	@Override
	public List<DailyQuoteRecord> findAllByCompany(Company company) {
		return ((DailyQuoteRecordDao) this.dao).findAllByCompany(company);
	}

	@Override
	public List<DailyQuoteRecord> findByCompanyOlderThan(Company company, Date maxDate, int maxCount) {
		return ((DailyQuoteRecordDao) this.dao).findByCompanyOlderThan(company, maxDate, maxCount);
	}

	@Override
	public List<DailyQuoteRecord> findByDateAndIds(Date from, Date to, Integer[] companyIds) {
		return ((DailyQuoteRecordDao) this.dao).findByDateAndIds(from, to, companyIds);
	}

	@Override
	public List<DailyQuoteRecord> findByDatePeriod(Date from, Date to) {
		return ((DailyQuoteRecordDao) this.dao).findByDatePeriod(from, to);
	}
	
}
