package org.vaadin.artur.exampledata;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriUtils;

public class ExampleDataGeneratorTest {

    public static class AllDataTypes {
        private Integer id;
        private String firstName, lastName, fullName, companyName, domain, accountNumber, occupation, tranasctionStatus,
                email, profilePictureURL, phoneNumber;
        private int amountOfMoney;
        private LocalDate dateOfBirth;
        private double price;
        private String ean;
        private String word, twoWords, sentence;
        private int lessThan10;
        private int lessThan100;
        private int lessThan1000;
        private int lessThan10000;
        private String foodProductName, foodProductEan, foodProductImageUrl;
        private String bookTitle, bookCoverImage;
        private boolean boolean5050, boolean9010, boolean1090;

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public String getEan() {
            return ean;
        }

        public void setEan(String ean) {
            this.ean = ean;
        }

        public void setAmountOfMoney(int amountOfMoney) {
            this.amountOfMoney = amountOfMoney;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getTwoWords() {
            return twoWords;
        }

        public void setTwoWords(String twoWords) {
            this.twoWords = twoWords;
        }

        public String getSentence() {
            return sentence;
        }

        public void setSentence(String sentence) {
            this.sentence = sentence;
        }

        public int getLessThan10() {
            return lessThan10;
        }

        public void setLessThan10(int lessThan10) {
            this.lessThan10 = lessThan10;
        }

        public int getLessThan100() {
            return lessThan100;
        }

        public void setLessThan100(int lessThan100) {
            this.lessThan100 = lessThan100;
        }

        public int getLessThan1000() {
            return lessThan1000;
        }

        public void setLessThan1000(int lessThan1000) {
            this.lessThan1000 = lessThan1000;
        }

        public int getLessThan10000() {
            return lessThan10000;
        }

        public void setLessThan10000(int lessThan10000) {
            this.lessThan10000 = lessThan10000;
        }

        public String getFoodProductName() {
            return foodProductName;
        }

        public void setFoodProductName(String foodProductName) {
            this.foodProductName = foodProductName;
        }

        public String getFoodProductEan() {
            return foodProductEan;
        }

        public void setFoodProductEan(String foodProductEan) {
            this.foodProductEan = foodProductEan;
        }

        public String getFoodProductImageUrl() {
            return foodProductImageUrl;
        }

        public void setFoodProductImageUrl(String foodProductImageUrl) {
            this.foodProductImageUrl = foodProductImageUrl;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBookCoverImage() {
            return bookCoverImage;
        }

        public void setBookCoverImage(String bookCoverImage) {
            this.bookCoverImage = bookCoverImage;
        }

        public boolean isBoolean5050() {
            return boolean5050;
        }

        public void setBoolean5050(boolean boolean5050) {
            this.boolean5050 = boolean5050;
        }

        public boolean isBoolean1090() {
            return boolean1090;
        }

        public void setBoolean1090(boolean boolean1090) {
            this.boolean1090 = boolean1090;
        }

        public boolean isBoolean9010() {
            return boolean9010;
        }

        public void setBoolean9010(boolean boolean9010) {
            this.boolean9010 = boolean9010;
        }

        @Override
        public String toString() {
            return "AllDataTypes [accountNumber=" + accountNumber + ", amountOfMoney=" + amountOfMoney
                    + ", bookCoverImage=" + bookCoverImage + ", bookTitle=" + bookTitle + ", boolean1090=" + boolean1090
                    + ", boolean5050=" + boolean5050 + ", boolean9010=" + boolean9010 + ", companyName=" + companyName
                    + ", dateOfBirth=" + dateOfBirth + ", domain=" + domain + ", ean=" + ean + ", email=" + email
                    + ", firstName=" + firstName + ", foodProductEan=" + foodProductEan + ", foodProductImageUrl="
                    + foodProductImageUrl + ", foodProductName=" + foodProductName + ", fullName=" + fullName + ", id="
                    + id + ", lastName=" + lastName + ", lessThan10=" + lessThan10 + ", lessThan100=" + lessThan100
                    + ", lessThan1000=" + lessThan1000 + ", lessThan10000=" + lessThan10000 + ", occupation="
                    + occupation + ", phoneNumber=" + phoneNumber + ", price=" + price + ", profilePictureURL="
                    + profilePictureURL + ", sentence=" + sentence + ", tranasctionStatus=" + tranasctionStatus
                    + ", twoWords=" + twoWords + ", word=" + word + "]";
        }

    }

    @Test
    public void allTypesEntity() throws UnsupportedEncodingException {
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
        generator.setData(AllDataTypes::setPrice, DataType.PRICE);
        generator.setData(AllDataTypes::setEan, DataType.EAN13);
        generator.setData(AllDataTypes::setWord, DataType.WORD);
        generator.setData(AllDataTypes::setTwoWords, DataType.TWO_WORDS);
        generator.setData(AllDataTypes::setSentence, DataType.SENTENCE);
        generator.setData(AllDataTypes::setLessThan10, DataType.NUMBER_UP_TO_10);
        generator.setData(AllDataTypes::setLessThan100, DataType.NUMBER_UP_TO_100);
        generator.setData(AllDataTypes::setLessThan1000, DataType.NUMBER_UP_TO_1000);
        generator.setData(AllDataTypes::setLessThan10000, DataType.NUMBER_UP_TO_10000);
        generator.setData(AllDataTypes::setFoodProductEan, DataType.FOOD_PRODUCT_EAN);
        generator.setData(AllDataTypes::setFoodProductImageUrl, DataType.FOOD_PRODUCT_IMAGE);
        generator.setData(AllDataTypes::setFoodProductName, DataType.FOOD_PRODUCT_NAME);
        generator.setData(AllDataTypes::setBookTitle, DataType.BOOK_TITLE);
        generator.setData(AllDataTypes::setBookCoverImage, DataType.BOOK_IMAGE_URL);
        generator.setData(AllDataTypes::setBoolean5050, DataType.BOOLEAN_50_50);
        generator.setData(AllDataTypes::setBoolean9010, DataType.BOOLEAN_90_10);
        generator.setData(AllDataTypes::setBoolean1090, DataType.BOOLEAN_10_90);

        AllDataTypes allDataTypes = generator.createBean(2015781843);
        LoggerFactory.getLogger(getClass()).info("Created entity {}", allDataTypes);
        Assert.assertEquals(1, allDataTypes.getId().intValue());
        Assert.assertEquals("Nell", allDataTypes.getFirstName());
        Assert.assertEquals("Testi", allDataTypes.getLastName());
        Assert.assertEquals("Business Unit Manager", allDataTypes.getOccupation());
        Assert.assertEquals("IL62 0108 0000 0009 9999 999", allDataTypes.getAccountNumber());
        Assert.assertEquals("Linens 'n Things Inc.", allDataTypes.getCompanyName());
        Assert.assertEquals("cunkob.li", allDataTypes.getDomain());
        Assert.assertEquals("Confirmed", allDataTypes.getTranasctionStatus());
        Assert.assertEquals("nell.testi@cunkob.li", allDataTypes.getEmail());
        Assert.assertEquals("https://images.unsplash.com/photo-1550639524-a6f58345a2ca?w=300",
                allDataTypes.getProfilePictureURL());
        Assert.assertEquals(52945, allDataTypes.getAmountOfMoney());
        Assert.assertEquals(LocalDate.of(1974, 4, 13), allDataTypes.getDateOfBirth());
        Assert.assertEquals("(660) 856-4069", allDataTypes.getPhoneNumber());
        Assert.assertEquals("Nell Testi", allDataTypes.getFullName());
        Assert.assertEquals(262.82, allDataTypes.getPrice(), 0);
        Assert.assertEquals("5246924979151", allDataTypes.getEan());
        Assert.assertEquals("cunkob", allDataTypes.getWord());
        Assert.assertEquals("apiifu uvicizati", allDataTypes.getTwoWords());
        Assert.assertEquals(
                "Ankobuki noivrok biveguod bubel se icwibjiz jopken puile luzci su jamafocil jamki igvo doswaczuk zohrogoc.",
                allDataTypes.getSentence());
        Assert.assertEquals(6, allDataTypes.getLessThan10());
        Assert.assertEquals(53, allDataTypes.getLessThan100());
        Assert.assertEquals(525, allDataTypes.getLessThan1000());
        Assert.assertEquals(5247, allDataTypes.getLessThan10000());
        Assert.assertEquals("Taillefine", allDataTypes.getFoodProductName());
        Assert.assertEquals("https://static.openfoodfacts.org/images/products/15054313/front_fr.4.400.jpg",
                allDataTypes.getFoodProductImageUrl());
        Assert.assertEquals("15054313", allDataTypes.getFoodProductEan());
        Assert.assertEquals("Becoming one with measuring things", allDataTypes.getBookTitle());
        MatcherAssert.assertThat(allDataTypes.getBookCoverImage(), CoreMatchers.not(CoreMatchers.containsString("#")));
        MatcherAssert.assertThat(allDataTypes.getBookCoverImage(), CoreMatchers.startsWith("data:image/svg+xml;utf8,"));

        String decodedImage = UriUtils.decode(allDataTypes.getBookCoverImage().replace("data:image/svg+xml;utf8,", ""),
                StandardCharsets.UTF_8);
        MatcherAssert.assertThat(decodedImage, CoreMatchers.containsString(allDataTypes.getFullName()));
        MatcherAssert.assertThat(decodedImage, CoreMatchers.containsString(allDataTypes.getBookTitle()));
        MatcherAssert.assertThat(decodedImage, CoreMatchers.containsString(
                DataType.BOOK_IMAGE_BACKGROUND.getValue(new Random(), 2015781843).replace("&", "&amp;")));
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
