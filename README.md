# PositionSearchApplication

## Az alkalmazás futtatásához a következő lépéseket kell végrehajtani: ##

1. Klónozzuk le a repository-t a GitHub-ról a saját számítógépre:

   ```
   git clone https://github.com/sisanny/OrdersApplication.git
   ```

2. Nyissuk meg az Intellij IDEA-t, és válasszuk a `File -> Open` menüt.

3. Keresünk és válasszuk ki az alkalmazásunk mappáját.

4. Várjuk meg, amíg a projekt betöltődik az Intellij IDEA-ba.

5. Nyissuk meg a `src/main/resources/application.properties` fájlt, és ellenőrizzük, hogy a következő adatok helyesek-e:

   ```
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.defer-datasource-initialization=true
   spring.sql.init.mode=always
   spring.jpa.hibernate.ddl-auto=create-drop
   ```

6. Ha az alkalmazásunkat az Intellij IDEA-ban szeretnénk futtatni, kattintsunk a jobb felső sarokban található zöld "Run" gombra, majd válasszuk ki az `Application` opciót. Az alkalmazásunk most már fut, és elérhető a `localhost:8080` webcímen.

   ```
  