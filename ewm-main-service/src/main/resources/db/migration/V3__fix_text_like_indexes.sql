-- Убираем индексы, которые ломают вставку длинных annotation/description
DROP INDEX IF EXISTS idx_events_annotation_like;
DROP INDEX IF EXISTS idx_events_description_like;
-- (title можно оставить; на тесты не влияет)
-- DROP INDEX IF EXISTS idx_events_title_like;
