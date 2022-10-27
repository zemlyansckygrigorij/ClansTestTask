DROP TABLE if exists public.clans;
DROP TABLE if exists public.users;
DROP SEQUENCE if exists public.clans_id_seq;
DROP SEQUENCE if exists public.users_id_seq;

CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.users_id_seq
    OWNER TO postgres;

CREATE SEQUENCE public.clans_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.clans_id_seq
    OWNER TO postgres;

CREATE TABLE public.clans
(
    id integer NOT NULL DEFAULT nextval('clans_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    gold integer,
    CONSTRAINT clans_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.clans
    OWNER to postgres;


CREATE TABLE public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    gold integer,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;