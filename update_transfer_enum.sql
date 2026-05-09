-- Script para actualizar el constraint del enum TypeTransfer en la base de datos
-- Ejecuta este script en PostgreSQL conectado a la base de datos macabi_db

-- 1. Eliminar el constraint antiguo
ALTER TABLE transfers DROP CONSTRAINT IF EXISTS transfers_type_transfer_check;

-- 2. Actualizar datos existentes (si los hay)
UPDATE transfers SET type_transfer = 'OUTBOUND' WHERE type_transfer = 'ONE_WAY';
UPDATE transfers SET type_transfer = 'RETURN' WHERE type_transfer = 'ROUND_TRIP';

-- 3. Agregar el nuevo constraint con los valores actualizados
ALTER TABLE transfers ADD CONSTRAINT transfers_type_transfer_check 
  CHECK (type_transfer IN ('OUTBOUND', 'RETURN'));

-- Verificar el resultado
SELECT constraint_name, check_clause 
FROM information_schema.check_constraints 
WHERE constraint_name = 'transfers_type_transfer_check';
