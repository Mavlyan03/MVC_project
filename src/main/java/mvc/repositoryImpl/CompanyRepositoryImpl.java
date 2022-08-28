package mvc.repositoryImpl;


import mvc.model.Company;

import mvc.repository.CompanyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCompany(Company company) {
        entityManager.persist(company);
    }

    @Override
    public void updateCompany(Long id,Company company) {
        Company company1 = entityManager.find(Company.class,id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company1);
    }

    @Override
    public List<Company> getAllCompanies() {
        return entityManager.createQuery("SELECT c FROM Company c",Company.class).getResultList();
    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class,id);
    }


    @Override
    public void deleteCompanyById(Long id) {
        entityManager.remove(entityManager.find(Company.class,id));
    }

    @Override
    public void countOfStudents() {
        entityManager.createQuery("SELECT COUNT(Company.students.size) FROM Company",Company.class).getResultList();
    }
}
