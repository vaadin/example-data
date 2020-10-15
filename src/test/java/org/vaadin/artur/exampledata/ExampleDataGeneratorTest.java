package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class ExampleDataGeneratorTest {

    public static class AllDataTypes {
        private Integer id;
        private String firstName, lastName, fullName, companyName, domain, accountNumber, occupation, tranasctionStatus,
                email, profilePictureURL, phoneNumber;
        private int amountOfMoney;
        private LocalDate dateOfBirth;

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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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
                    + ", companyName=" + companyName + ", dateOfBirth=" + dateOfBirth + ", domain=" + domain
                    + ", email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
                    + ", occupation=" + occupation + ", phoneNumber=" + phoneNumber + ", profilePictureURL="
                    + profilePictureURL + ", tranasctionStatus=" + tranasctionStatus + "]";
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    @Test
    public void allTypesEntity() {
        ExampleDataGenerator<AllDataTypes> generator = new ExampleDataGenerator<>(AllDataTypes.class);
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
        generator.setData(AllDataTypes::setDateOfBirth, DataType.DATE_OF_BIRTH);
        generator.setData(AllDataTypes::setPhoneNumber, DataType.PHONE_NUMBER);
        generator.setData(AllDataTypes::setFullName, DataType.FULL_NAME);

        AllDataTypes allDataTypes = generator.createBean(2015781843);
        LoggerFactory.getLogger(getClass()).info("Created entity {}", allDataTypes);
        Assert.assertEquals(1, allDataTypes.getId().intValue());
        Assert.assertEquals("Nell", allDataTypes.getFirstName());
        Assert.assertEquals("Testi", allDataTypes.getLastName());
        Assert.assertEquals("Senior Financial Analyst", allDataTypes.getOccupation());
        Assert.assertEquals("IL62 0108 0000 0009 9999 999", allDataTypes.getAccountNumber());
        Assert.assertEquals("Linens 'n Things Inc.", allDataTypes.getCompanyName());
        Assert.assertEquals("cunkob.li", allDataTypes.getDomain());
        Assert.assertEquals("Pending", allDataTypes.getTranasctionStatus());
        Assert.assertEquals("nell.testi@cunkob.li", allDataTypes.getEmail());
        Assert.assertEquals("https://images.unsplash.com/photo-1550639524-a6f58345a2ca?w=300",
                allDataTypes.getProfilePictureURL());
        Assert.assertEquals(36221, allDataTypes.getAmountOfMoney());
        Assert.assertEquals(LocalDate.of(1974, 4, 13), allDataTypes.getDateOfBirth());
        Assert.assertEquals("(660) 856-4069", allDataTypes.getPhoneNumber());
        Assert.assertEquals("Nell Testi", allDataTypes.getFullName());
    }

    @Test
    public void allTypesManyEntities() {
        ExampleDataGenerator<AllDataTypes> generator = new ExampleDataGenerator<>(AllDataTypes.class);
        generator.setData(AllDataTypes::setId, DataType.ID);
        generator.setData(AllDataTypes::setFirstName, DataType.FIRST_NAME);
        generator.setData(AllDataTypes::setLastName, DataType.LAST_NAME);
        generator.setData(AllDataTypes::setFullName, DataType.FULL_NAME);
        generator.setData(AllDataTypes::setOccupation, DataType.OCCUPATION);
        generator.setData(AllDataTypes::setAccountNumber, DataType.IBAN);
        generator.setData(AllDataTypes::setCompanyName, DataType.COMPANY_NAME);
        generator.setData(AllDataTypes::setDomain, DataType.DOMAIN);
        generator.setData(AllDataTypes::setTranasctionStatus, DataType.TRANSACTION_STATUS);
        generator.setData(AllDataTypes::setEmail, DataType.EMAIL);
        generator.setData(AllDataTypes::setProfilePictureURL, DataType.PROFILE_PICTURE_URL);
        generator.setData(AllDataTypes::setAmountOfMoney, DataType.AMOUNT_OF_MONEY);
        generator.setData(AllDataTypes::setDateOfBirth, DataType.DATE_OF_BIRTH);
        generator.setData(AllDataTypes::setPhoneNumber, DataType.PHONE_NUMBER);
        List<AllDataTypes> entities = generator.create(10, 123);

        Assert.assertEquals(10, entities.size());
        for (AllDataTypes allDataTypes : entities) {
            Assert.assertNotNull(allDataTypes.getId());
            Assert.assertNotNull(allDataTypes.getFirstName());
            Assert.assertNotNull(allDataTypes.getLastName());
            Assert.assertNotNull(allDataTypes.getFullName());
            Assert.assertNotNull(allDataTypes.getOccupation());
            Assert.assertNotNull(allDataTypes.getAccountNumber());
            Assert.assertNotNull(allDataTypes.getCompanyName());
            Assert.assertNotNull(allDataTypes.getDomain());
            Assert.assertNotNull(allDataTypes.getTranasctionStatus());
            Assert.assertNotNull(allDataTypes.getEmail());
            Assert.assertNotNull(allDataTypes.getProfilePictureURL());
            Assert.assertTrue(allDataTypes.getAmountOfMoney() > 0);
            Assert.assertNotNull(allDataTypes.getDateOfBirth());
            Assert.assertNotNull(allDataTypes.getPhoneNumber());
        }
    }
}
