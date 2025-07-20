INSERT INTO my_aggregate_root(my_aggregate_root_id, removed_at)
VALUES ('ag1', null);

INSERT INTO my_foo_entity(my_foo_entity_id, my_aggregate_root_id, removed_at)
VALUES ('f1', 'ag1', null),
       ('f2', 'ag1', null),
       ('f3', 'ag1', null);

INSERT INTO my_bar_entity(my_bar_entity_id, my_aggregate_root_id, removed_at)
VALUES ('b1', 'ag1', null),
       ('b2', 'ag1', null),
       ('b3', 'ag1', null);
