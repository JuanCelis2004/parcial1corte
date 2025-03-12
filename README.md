# Instrucciones de despliegue para un entorno local

El proyecto se desarrollo en netbeans con la versión 24 con su respectivo JDK-21 con el cual se despliega el el servidor de tomcat con la version Apache Tomcat/8.5.96

Luego de tener esos datos se procede a clonar el repositorio, luego de ser clonado se habre en NetBeans y abre XAMPP y entramos a MySQL para poder crear una base de datos preferiblemente que se llame "parcialdb".

Despues vamos a conectar la base de datos en NetBeans vamos a "Services" en "Databases" clic derecho "new" y procedemos a conectar la base de datos. 


Despues seguimos con la configuración del "persistence.xml" donde tendremos que verificar que la conexion con la base de datos este correctamente del no ser el caso podemos eliminar dicho archivo y crear uno nuevo para mejor configuración del proyecto el cual se hara de la siguinete manera(estos pasos a continuación mas que todo son usandos si cambiaste el nombre de la base de datos):

1. Damos clic derecho sobre el proyecto y le damos "new" 
2. luego en la opcion "other" 
3. en el lado izquierdo buscamos la carpeta "Persistence" 
4. para seleccionar en el lado derecho una "Persistence Unit"
5. Vamos a ponerle un nombre preferiblemnte que sea "parcialdbPU" 
6. en la parte de Database Connection selecionamos la base de datos que ya prebiamente configuramos.
7. luego en ese mismo archivo "persistence.xml" nos vamos a la pestaña de "source" donde nos aparecera esta linea de codigo "<!-- <property name="jakarta.persistence.schema-generation.database.action" value="create"/> -->" la cual vamos a remplazar por "<!-- <property name="javax.persistence.schema-generation.database.action" value="create"/> -->"(este paso es muy importante para que funcione correctamente);

Ya para finalizar nos vamos a "/Source Packages/persistende/reservationModelJpaController" y pegamos el nombre que le dimos a nuestra Persistence Unit en la parte de la linea 38 que encontraremos este codigo "emf=Persistence.createEntityManagerFactory("parcialdbPU");" y lo que esta en comillas dobles es donde podremos el nombre de nuestra PU.

Luego le das clic derecho al proyecto le das "clean and build" esperas que cargue y ya le das "RUN"

