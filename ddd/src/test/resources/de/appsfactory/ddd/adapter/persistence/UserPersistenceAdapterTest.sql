TRUNCATE TABLE USER;
insert into USER (ID,ADDRESS1, ADDRESS2, ADDRESS3, CITY, COUNTRYISOCODE, COUNTRY_NAME, DOB, EMAIL, FIRST_NAME, HOMEPAGE, LAST_NAME, POSTCODE)
values (1, '23 safe street', '23 safe street', 'near ALDI', 'Leipzig', 'DE', 'Germany', to_date('28-JUL-00','DD-MON-RR'), 'test@test.com', 'Jack', 'https://www.mypage.com','Darcy', '045582');
