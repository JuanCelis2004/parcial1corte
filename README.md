# Instrucciones de despliegue para un entorno local en Tomcat

El proyecto se desarrolló en NetBeans con la versión 24 con su respectivo JDK-21 con el cual se despliega en el servidor de Tomcat con la versión Apache Tomcat/8.5.96

Luego de tener esos datos se procede a clonar el repositorio con el siguiente comando "git clone https://github.com/JuanCelis2004/parcial1corte.git", luego de ser clonado se abre en NetBeans y abre XAMPP y entramos a MySQL para poder crear una base de datos preferiblemente que se llame "parcialdb".

Después vamos a conectar la base de datos en NetBeans vamos a "Services" en "Databases" clic derecho "new" y procedemos a conectar la base de datos. 


Después seguimos con la configuración del "persistence.xml" donde tendremos que verificar que la conexión con la base de datos este correctamente del no ser el caso podemos eliminar dicho archivo y crear uno nuevo para mejor configuración del proyecto el cual se hará de la siguiente manera (estos pasos a continuación más que todo son usan dos si cambiaste el nombre de la base de datos):

1. Damos clic derecho sobre el proyecto y le damos "new" 
2. luego en la opción "other" 
3. en el lado izquierdo buscamos la carpeta "Persistence" 
4. para seleccionar en el lado derecho una "Persistence Unit"
5. Vamos a ponerle un nombre preferiblemente que sea "parcialdbPU" 
6. en la parte de Database Connection seleccionamos la base de datos que ya previamente configuramos.
7. luego en ese mismo archivo "persistence.xml" nos vamos a la pestaña de "source" donde nos aparecera esta linea de codigo "<!-- <property name="jakarta.persistence.schema-generation.database.action" value="create"/> -->" la cual vamos a remplazar por "<!-- <property name="javax.persistence.schema-generation.database.action" value="create"/> -->"(este paso es muy importante para que funcione correctamente);

Ya para finalizar nos vamos a "/Source Packages/persistende/reservationModelJpaController" y pegamos el nombre que le dimos a nuestra Persistence Unit en la parte de la línea 38 que encontraremos este código "emf=Persistence.createEntityManagerFactory("parcialdbPU");" y lo que está en comillas dobles es donde podremos el nombre de nuestra PU.

Luego le das clic derecho al proyecto le das "clean and build" esperas que cargue y ya le das "RUN"

Para mayor seguridad el proyecto se despliega con esta URL: http://localhost:8080/firstParcial/index.jsp


