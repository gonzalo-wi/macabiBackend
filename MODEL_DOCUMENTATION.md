# Modelo de Base de Datos - Sistema de Gestión de Proyectos

## 📋 Descripción General
Sistema para gestionar proyectos que incluye participantes, menús de comidas, transporte y productos del stock general.

---

## 🗂️ Entidades y Relaciones

### **User** (Usuarios del sistema)
- **Tabla:** `users`
- **Campos:**
  - `id` (PK, auto-increment)
  - `name` (obligatorio)
  - `first_name` (obligatorio)
  - `email` (obligatorio, único)
  - `password` (obligatorio)
  - `role` (ADMIN, USER)
- **Relaciones:**
  - Un usuario puede crear muchos proyectos (1:N con Project)
  - Un usuario puede participar en muchos proyectos (N:M a través de Participant)

---

### **Project** (Proyectos)
- **Tabla:** `projects`
- **Campos:**
  - `id` (PK, auto-increment)
  - `name` (obligatorio)
  - `description` (texto largo)
  - `date` (obligatorio)
  - `user_id` (FK a User - creador del proyecto)
- **Relaciones:**
  - Pertenece a un usuario creador (N:1 con User)
  - Tiene muchos participantes (1:N con Participant)
  - Tiene muchos menús (1:N con Menu)
  - Tiene muchos transportes (1:N con Transfer)
  - Puede reservar muchos productos (1:N con ProjectProduct)

---

### **Participant** (Participantes en proyectos)
- **Tabla:** `participants`
- **Campos:**
  - `id` (PK, auto-increment)
  - `project_id` (FK a Project)
  - `user_id` (FK a User)
  - `name` (obligatorio)
  - `first_name` (obligatorio)
  - `email` (obligatorio)
- **Relaciones:**
  - Pertenece a un proyecto (N:1 con Project)
  - Asociado a un usuario (N:1 con User)
  - Puede hacer muchas reservas de comida (1:N con BookingMeal)
  - Puede hacer muchas reservas de transporte (1:N con BookingTransfer)
- **Nota:** Esta tabla relaciona usuarios con proyectos y permite datos adicionales específicos del participante

---

### **Menu** (Menús de comidas para un proyecto)
- **Tabla:** `menus`
- **Campos:**
  - `id` (PK, auto-increment)
  - `project_id` (FK a Project)
  - `date` (obligatorio)
  - `type_menu` (DINNER, LUNCH)
  - `active` (boolean, default true)
- **Relaciones:**
  - Pertenece a un proyecto (N:1 con Project)
  - Tiene muchas comidas disponibles (N:M con Meal a través de menu_meals)

---

### **Meal** (Tipos de comidas disponibles)
- **Tabla:** `meals`
- **Campos:**
  - `id` (PK, auto-increment)
  - `name` (obligatorio)
  - `description` (texto largo)
  - `image_url`
  - `clasification`
- **Relaciones:**
  - Puede estar en muchos menús (N:M con Menu)
  - Puede ser reservada muchas veces (1:N con BookingMeal)
- **Nota:** Catálogo general de comidas disponibles

---

### **Transfer** (Transportes para un proyecto)
- **Tabla:** `transfers`
- **Campos:**
  - `id` (PK, auto-increment)
  - `project_id` (FK a Project)
  - `origin` (obligatorio)
  - `destination` (obligatorio)
  - `date` (fecha y hora, obligatorio)
  - `active` (boolean, default true)
  - `type_transfer` (ONE_WAY, ROUND_TRIP)
  - `number_of_reservations` (contador)
  - `suggested_buses` (cálculo automático)
- **Relaciones:**
  - Pertenece a un proyecto (N:1 con Project)
  - Puede ser reservado muchas veces (1:N con BookingTransfer)

---

### **Booking** (Clase abstracta para reservas)
- **Tabla:** `bookings`
- **Campos:**
  - `id` (PK, auto-increment)
  - `participant_id` (FK a Participant)
  - `type_booking` (MEAL, TRANSFER)
  - `active` (boolean, default true)
- **Herencia:** JOINED (tabla separada para cada subclase)
- **Subclases:**
  - BookingMeal
  - BookingTransfer

---

### **BookingMeal** (Reservas de comidas)
- **Tabla:** `booking_meals`
- **Hereda de:** Booking
- **Campos adicionales:**
  - `meal_id` (FK a Meal)
  - `type_menu` (DINNER, LUNCH)
- **Relaciones:**
  - Hereda relación con Participant
  - Referencia a una comida específica (N:1 con Meal)

---

### **BookingTransfer** (Reservas de transporte)
- **Tabla:** `booking_transfers`
- **Hereda de:** Booking
- **Campos adicionales:**
  - `transfer_id` (FK a Transfer)
- **Relaciones:**
  - Hereda relación con Participant
  - Referencia a un transporte específico (N:1 con Transfer)

---

### **Product** (Productos en stock general)
- **Tabla:** `products`
- **Campos:**
  - `id` (PK, auto-increment)
  - `name` (obligatorio)
  - `description` (texto largo)
  - `amount` (stock disponible, obligatorio)
  - `active` (boolean, default true)
- **Relaciones:**
  - Puede ser reservado por muchos proyectos (1:N con ProjectProduct)
- **Nota:** Stock general compartido entre todos los proyectos

---

### **ProjectProduct** (Reservas de productos por proyecto)
- **Tabla:** `project_products`
- **Campos:**
  - `id` (PK, auto-increment)
  - `project_id` (FK a Project)
  - `product_id` (FK a Product)
  - `quantity` (cantidad reservada, obligatorio)
  - `active` (boolean, default true)
- **Relaciones:**
  - Pertenece a un proyecto (N:1 con Project)
  - Referencia a un producto (N:1 con Product)
- **Nota:** Tabla intermedia para gestionar la asignación de productos a proyectos

---

## 🔗 Diagrama de Relaciones

```
User (1) ----< (N) Project (1) ----< (N) Participant (N) >---- (1) User
                     |                        |
                     |                        +----< BookingMeal >---- Meal
                     |                        |
                     |                        +----< BookingTransfer >---- Transfer
                     |
                     +----< Menu (N) >----< (N) Meal
                     |
                     +----< Transfer
                     |
                     +----< ProjectProduct >---- Product (stock general)
```

---

## ✅ Cambios Realizados

### 1. **Anotaciones JPA añadidas a todas las entidades:**
   - `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
   - `@Column` con restricciones (nullable, unique, etc.)
   - `@Enumerated` para enums
   - Relaciones: `@ManyToOne`, `@OneToMany`, `@ManyToMany`

### 2. **Relaciones correctamente definidas:**
   - Project → User (creador)
   - Project → Participants, Menus, Transfers (1:N)
   - Participant → Project, User (N:1)
   - Menu → Project (N:1) y Menu ↔ Meal (N:M)
   - Transfer → Project (N:1)
   - Booking (abstracta) → Participant
   - BookingMeal → Meal
   - BookingTransfer → Transfer

### 3. **Nueva entidad creada:**
   - **ProjectProduct**: Para gestionar reservas de productos del stock general

### 4. **Tipos de datos corregidos:**
   - Fechas cambiadas de `String` a `LocalDate` y `LocalDateTime`
   - Agregados valores por defecto para campos booleanos

### 5. **Estrategia de herencia:**
   - Booking usa `JOINED` para mejor normalización

---

## 🚀 Próximos Pasos

1. **Configurar `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/controlpanel
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

2. **Crear los repositorios** (interfaces que extienden `JpaRepository`)

3. **Ejecutar la aplicación** para que Hibernate genere las tablas automáticamente

4. **Considerar agregar:**
   - Índices para mejorar el rendimiento
   - Auditoría (createdAt, updatedAt)
   - Soft deletes (deletedAt) si se requiere

---

## 📝 Notas Importantes

- **Participant vs User**: Participant es una tabla intermedia que relaciona Users con Projects y permite guardar información específica del participante en ese proyecto (por ejemplo, un email diferente al del usuario).

- **Stock de productos**: Todos los proyectos ven el mismo stock (tabla Product). La tabla ProjectProduct registra cuántos productos reservó cada proyecto.

- **Reservas de transporte**: Un participante puede reservar ida (ONE_WAY) o ida y vuelta (ROUND_TRIP) según el tipo de transfer.

- **Reservas de comidas**: Se identifican por el tipo de menú (DINNER/LUNCH) y la comida específica seleccionada.
