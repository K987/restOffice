package hu.restoffice.persistence.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.util.EntityManagerProvider;

import hu.restoffice.persistence.domain.User;

@RunWith(JUnit4.class)
public class DatabaseRiderCoreTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("riderDB");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());



    @Test
    // @DataSet({ "users.yml" })
    public void shouldListUsers() {
        System.out.println(emProvider.isEntityManagerActive());
        System.out.println(emProvider.getEm().isOpen());
        emProvider.getEm().createNamedQuery("User.findAll", User.class).getResultList();

    }
}