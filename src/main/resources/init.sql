
-- Insert Quests
INSERT INTO quest (id, name, progress, start_date)
VALUES
  (1, 'Frontend Overhaul', 55.0, CURRENT_DATE),
  (2, 'Backend Refactor', 60.0, CURRENT_DATE),
  (3, 'AI Integration', 90.0, CURRENT_DATE);

-- Insert Tasks for Quest 1
INSERT INTO task (id, name, description, status, done_date, revision_count, estimate_hours, actual_hours, quest_id)
VALUES
  (1, 'Redesign dashboard layout', '', 'UNASSIGNED', NULL, 0, 3, 3, 1),
  (2, 'Implement drag-drop', '', 'UNASSIGNED', NULL, 0, 3, 3, 1);

-- Insert Tasks for Quest 2
INSERT INTO task (id, name, description, status, done_date, revision_count, estimate_hours, actual_hours, quest_id)
VALUES
  (3, 'Modularize microservices', '', 'UNASSIGNED', NULL, 0, 3, 3, 2),
  (4, 'Add JWT authentication', '', 'UNASSIGNED', NULL, 0, 3, 3, 2);

-- Insert Tasks for Quest 3
INSERT INTO task (id, name, description, status, done_date, revision_count, estimate_hours, actual_hours, quest_id)
VALUES
  (5, 'Integrate LLM API', '', 'UNASSIGNED', NULL, 0, 3, 3, 3),
  (6, 'Design planning assistant', '', 'UNASSIGNED', NULL, 0, 3, 3, 3);
COMMIT;