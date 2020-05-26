package org.vaadin.artur.exampledata;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class ExampleDataGeneratorTest {

    public static class AllDataTypes {
        private Integer id;
        private String firstName, lastName, companyName, domain, accountNumber, occupation, tranasctionStatus, email,
                profilePictureURL;
        private int amountOfMoney;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getTranasctionStatus() {
            return tranasctionStatus;
        }

        public void setTranasctionStatus(String tranasctionStatus) {
            this.tranasctionStatus = tranasctionStatus;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProfilePictureURL() {
            return profilePictureURL;
        }

        public void setProfilePictureURL(String profilePictureURL) {
            this.profilePictureURL = profilePictureURL;
        }

        public int getAmountOfMoney() {
            return amountOfMoney;
        }

        public void setAmountOfMoney(Integer amountOfMoney) {
            this.amountOfMoney = amountOfMoney;
        }

        @Override
        public String toString() {
            return "AllDataTypes [accountNumber=" + accountNumber + ", amountOfMoney=" + amountOfMoney
                    + ", companyName=" + companyName + ", domain=" + domain + ", email=" + email + ", firstName="
                    + firstName + ", id=" + id + ", lastName=" + lastName + ", occupation=" + occupation
                    + ", profilePictureURL=" + profilePictureURL + ", tranasctionStatus=" + tranasctionStatus + "]";
        }

    }

    @Test
    public void allTypesEntity() {
        ExampleDataGenerator<AllDataTypes> generator = new ExampleDataGenerator<>(AllDataTypes.class, 123L);
        generator.setData(AllDataTypes::setId, DataType.ID);
        generator.setData(AllDataTypes::setFirstName, DataType.FIRST_NAME);
        generator.setData(AllDataTypes::setLastName, DataType.LAST_NAME);
        generator.setData(AllDataTypes::setOccupation, DataType.OCCUPATION);
        generator.setData(AllDataTypes::setAccountNumber, DataType.IBAN);
        generator.setData(AllDataTypes::setCompanyName, DataType.COMPANY_NAME);
        generator.setData(AllDataTypes::setDomain, DataType.DOMAIN);
        generator.setData(AllDataTypes::setTranasctionStatus, DataType.TRANSACTION_STATUS);
        generator.setData(AllDataTypes::setEmail, DataType.EMAIL);
        generator.setData(AllDataTypes::setProfilePictureURL, DataType.PROFILE_PICTURE_URL);
        generator.setData(AllDataTypes::setAmountOfMoney, DataType.AMOUNT_OF_MONEY);

        AllDataTypes allDataTypes = generator.create();
        LoggerFactory.getLogger(getClass()).info("Created entity {}", allDataTypes);
        Assert.assertEquals(1, allDataTypes.getId().intValue());
        Assert.assertEquals("Edward", allDataTypes.getFirstName());
        Assert.assertEquals("Duran", allDataTypes.getLastName());
        Assert.assertEquals("Health Insurance Adjuster", allDataTypes.getOccupation());
        Assert.assertEquals("LT12 1000 0111 0100 1000", allDataTypes.getAccountNumber());
        Assert.assertEquals("Ball Industries", allDataTypes.getCompanyName());
        Assert.assertEquals("ball-industries.edu", allDataTypes.getDomain());
        Assert.assertEquals("Confirmed", allDataTypes.getTranasctionStatus());
        Assert.assertEquals("ahmed.morgan@canadian-speciality-group.com", allDataTypes.getEmail());
        Assert.assertEquals("https://images.unsplash.com/photo-1575779977884-f1069c45cbf4?w=300",
                allDataTypes.getProfilePictureURL());
        Assert.assertEquals(25726, allDataTypes.getAmountOfMoney());

    }

    @Test
    public void allTypesManyEntities() {
        ExampleDataGenerator<AllDataTypes> generator = new ExampleDataGenerator<>(AllDataTypes.class, 123L);
        generator.setData(AllDataTypes::setId, DataType.ID);
        generator.setData(AllDataTypes::setFirstName, DataType.FIRST_NAME);
        generator.setData(AllDataTypes::setLastName, DataType.LAST_NAME);
        generator.setData(AllDataTypes::setOccupation, DataType.OCCUPATION);
        generator.setData(AllDataTypes::setAccountNumber, DataType.IBAN);
        generator.setData(AllDataTypes::setCompanyName, DataType.COMPANY_NAME);
        generator.setData(AllDataTypes::setDomain, DataType.DOMAIN);
        generator.setData(AllDataTypes::setTranasctionStatus, DataType.TRANSACTION_STATUS);
        List<AllDataTypes> entities = generator.create(10);

        Assert.assertEquals(10, entities.size());
        for (AllDataTypes allDataTypes : entities) {
            Assert.assertNotNull(allDataTypes.getId());
            Assert.assertNotNull(allDataTypes.getFirstName());
            Assert.assertNotNull(allDataTypes.getLastName());
            Assert.assertNotNull(allDataTypes.getOccupation());
            Assert.assertNotNull(allDataTypes.getAccountNumber());
            Assert.assertNotNull(allDataTypes.getCompanyName());
            Assert.assertNotNull(allDataTypes.getDomain());
            Assert.assertNotNull(allDataTypes.getTranasctionStatus());
            Assert.assertNotNull(allDataTypes.getAmountOfMoney());
        }
    }
}