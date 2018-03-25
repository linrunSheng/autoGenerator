cd e:
cd e:\git-new-res\crud-generator\crud-plugin
mvn clean install
cd e:\git-new-res\crud-generator\crud-example
mvn mybatis-generator:generate
cd e:\git-new-res\crud-generator\crud-gen
mvn spring-boot:run
cd e:\git-new-res\crud-generator\crud-example
mvn spring-boot:run

