# ejercicios_api_y_selenium
Ejercicios de api con postman y selenium webdriver

## Test API
En la carpeta **newman** se encuentra la colección y las variables de ambiente en formato json. Estas se pueden importar y ejecutar desde **postman** o ejecutarlas desde una línea de comandos con **newman**.

Para utilizar **newman** debe instalar lo siguiente en su equipo:

- **Node.js**
- **npm**

Dentro de la carpeta newman instalamos la paquetería de newman con el comando  **npm i newman**  desde una terminal.

Para correr la colección ejecutamos el comando **newman run Pokeapi.postman_collection.json -e pokeapi_env.postman_environment.json**

## Selenium Webdriver con Java, Spring Framework y Cucumber


En la carpeta **selenium_sauce_demo** se encuentra todo lo necesario para ejecutar los test cases con selenium webdriver y Java. Este proyecto se desarrolló utilizando **maven**, en nuestro archivo **pom.xml** se encuntran todas las dependencias necesarias para que este proyecto funcione.
En la carpeta **src** está todo el codigo estructurado mediante el patrón de diseño **POM**, el cual se divide en **main** y **test**.
#### Main
Dentro de la carpeta main podemos encontrar paquetes y clases relacionadas con la configuración. Por un lado está la carpeta **java** con todos los paquetes, clases y codigo relacionados con la automatización y por otro lado tenemos los **recursos** necesarios (no es codigo) para que funcione nuestro proyecto.

##### Java folder
###### Automation
Aquí se encuentran los paquetes:
-	**Config**. – Dentro tenemos una clase  llamada **AutomationFrameworkConfiguration** la cual scaneara todos los componentes dentro de **Automation**. Es necesaria para la creación de **BDD** test cases
-	**Drivers** – Dentro de esta carpeta tenemos todo lo necesario para inicializar nuestros drivers, siendo nuestro archivo principal **DriverSingleton** el cual unifica todo lo necesario para inicializar el driver de **Chrome** o **Firefox**.
-	**Pages** – Aquí se encuentran todas las clases correspondientes a cada página del sitio web, con los locator y metodos necesarios para realizar las pruebas.
-	**Utils** – Tenemos una clase llamada **ConfigurationProperties** la cual nos ayuda a obtener las cadenas de texto que se encuentran en el archivo **framework.properties**. Esto nos es útil como buena práctica para acceder a cierta informacion en vez de escribir los valores de manera hardcodeada directamente en las pruebas. La clase **Constants** conserva variables constantes requeridas por ciertos métodos, de igual manera para evitar hardcodear. Por último la clase **Utils** contiene un metodo que nos ayuda a decodificar el Password necesario para nuestro login, ya que como buena práctica el Password lo tenemos escrito en nuestro archivo **framework.properties** codificado con **base64**.

##### Resources folder
Dentro de la carpeta resources tenemos los **drivers**, un archivo llamado **framework.properties** (*En este archivo se encuentran todas las cadenas de texto utilizadas como parámetros para los métodos y assertions, se mandan a llamar desde este archivo para evitar harcodear*) y la carpeta **features** con un archivo llamado **Login.feature** el cual es interpretado por **cucumber** para gestionar las pruebas a través de **Gherkin**.

#### Test
Dentro de la carpeta **test** solo tenemos dos clases:
-	**Test/java/automation/glue/StepDefinition** – La razon por la que esta clase está dentro de una carpeta llamada **glue**, es por convención y hace referencia a que es la unión hacia nuestro archivo **Login.feature** donde están nuestras *historias de usuario*. En nuestra clase **StepDefinition** tenemos definidos nuestros **BDD Tests** los cuales siguen la convención de **Gherkin** usando el proceso **Given-When-Then**, aquí importamos la mayoría de las clases definidas en el proyecto, para inicializar el webdriver, definir las pruebas a través de los métodos creados en cada página, assertions etc. Cada notación **Given-When-Then** y **And** va ligada a un paso dentro de las historias de usuario definidas en el archivo de cucumber **Login.feature**
-	**Test/java/Automation/RunTest**– En esta clase se unifican todas las pruebas con un metodo test, desde ahí se ejecutan las pruebas. Se añaden ciertas opciones para cucumber, como la ruta del reporteo y el path del archivo de las historias de usuario.
