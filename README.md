# Gestión de Transacciones y Rollback en JDBC (Repaso 20)

Programa en Java que implementa el control de transacciones manuales para asegurar la integridad y consistencia de los datos ante errores durante procesos de inserción masiva.

## 🚀 Funcionalidades
* **Control de Transacciones Manual:** Desactiva el `autoCommit` de JDBC para agrupar múltiples operaciones en una única unidad lógica de trabajo.
* **Inserción Masiva Segura:** Permite al usuario definir una cantidad de registros a insertar, procesándolos de forma atómica.
* **Mecanismo de Rollback:** En caso de que ocurra cualquier error (como una clave primaria duplicada o fallo de red), el programa deshace automáticamente todas las inserciones previas de esa sesión, restaurando el estado original de la base de datos.
* **Confirmación por Commit:** Solo si todas las operaciones del ciclo finalizan con éxito, los cambios se graban permanentemente en el motor de base de datos.

## 🛠️ Estructura técnica
El proyecto demuestra el uso de las propiedades transaccionales de SQL mediante Java:
* **`conn.setAutoCommit(false)`**: Punto de inicio de la transacción manual, evitando que cada `executeUpdate` sea definitivo.
* **`conn.commit()`**: Valida y hace permanentes todos los cambios realizados desde el inicio de la transacción.
* **`conn.rollback()`**: Función crítica ejecutada dentro del bloque `catch` para revertir los cambios ante excepciones de SQL.
* **Gestión de Excepciones Anidada**: Utiliza bloques `try-catch` internos para separar los errores de conexión de los errores lógicos durante la ejecución de las sentencias.