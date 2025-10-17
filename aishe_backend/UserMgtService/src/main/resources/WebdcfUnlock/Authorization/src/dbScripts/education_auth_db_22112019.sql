--education_auth_db;

--Table 1 #### users
CREATE TABLE users
(
    id bigint primary key,
	fk_registered_site_id bigint,
    login_id varchar(20) UNIQUE,
    name varchar(75),
    password varchar(60),
	password_two varchar(60),
	password_three varchar(60),
	vernacular_name jsonb,
    created_by bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

--Table 2 #### users_contacts
CREATE TABLE users_contacts
(
    id bigint primary key,
	fk_user_id bigint,
    primary_email_address varchar,
    recovery_email_address varchar,
    primary_mobile_number varchar,
    recovery_mobile_number varchar,
	primary_email_address_verified_on timestamp without time zone,
    recovery_email_address_verified_on timestamp without time zone,
    primary_mobile_number_verified_on timestamp without time zone,
    recovery_mobile_number_verified_on timestamp without time zone,
    is_primary_email_address_verified boolean,
    is_recovery_email_address_verified boolean,
    is_primary_mobile_number_verified boolean,
    is_recovery_mobile_number_verified boolean,
    created_by bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

--Table 3 #### otp_details
CREATE TABLE otp_details
(
    id serial primary key,
    fk_user_id bigint,
    type varchar(50),
    otp varchar(10),
    create_date timestamp without time zone,
    expires_on timestamp without time zone
);

--Table 4 #### registration_site_details
CREATE TABLE registration_site_details
(
    id bigint primary key,
    name varchar(75),
    created_by bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

--Table 5 #### registered_site_microservices_mapping

CREATE TABLE registered_site_microservices_mapping
(
    id bigint primary key,
    microservice_name varchar(75),
    microservice_urls varchar(100),
    fk_registration_site_id BIGINT,
    created_by bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

--Table 6 #### table_id_generation_counter
CREATE TABLE table_id_generation_counter
(
    id serial primary key,
    table_name varchar(75),
    id_counter bigint
);

CREATE TABLE login_page_header
(
    record_id serial primary key,
    header character varying(500) COLLATE pg_catalog."default",
    address character varying(500) COLLATE pg_catalog."default",
    office_level_name character varying(255) COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);


CREATE TABLE login_page_slider
(
    record_id serial primary key,
    sequence integer,
    banner_description character varying(5000) COLLATE pg_catalog."default",
    office_level_name character varying(255) COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

CREATE TABLE m_active_status
(
    record_id serial primary key,
    active_status character varying(10) COLLATE pg_catalog."default",
    is_active boolean DEFAULT true
);

CREATE TABLE m_menu_url_mapping
(
    record_id serial primary key,
    menu_name character varying(100) COLLATE pg_catalog."default",
    menu_url character varying(256) COLLATE pg_catalog."default",
    created_by character varying(128) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(128) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true,
    fk_office_type_id integer
);

CREATE TABLE m_user_menu
(
    record_id serial primary key,
    has_child boolean,
    id_value character varying(255) COLLATE pg_catalog."default",
    menu_block integer,
    menu_class character varying(255) COLLATE pg_catalog."default",
    menu_icon character varying(255) COLLATE pg_catalog."default",
    menu_location character varying(255) COLLATE pg_catalog."default",
    lang_code character varying(5) COLLATE pg_catalog."default",
    menu_name character varying(255) COLLATE pg_catalog."default",
    parent_id integer,
    fk_office_type_id integer,
    fk_eba_name_id integer,
    display_sequence integer,
	created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

CREATE TABLE m_user_menu_in_other_lang
(
    record_id serial primary key,
    fk_m_user_menu_record_id integer NOT NULL,
    lang_code character varying(10) COLLATE pg_catalog."default",
    menu_name character varying(255) COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

CREATE TABLE m_user_menu_privileges
(
    record_id serial primary key,
    action_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_value character varying(255) COLLATE pg_catalog."default" NOT NULL,
    action_class character varying(255) COLLATE pg_catalog."default" NOT NULL,
    action_url character(255) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean DEFAULT true
);

CREATE TABLE menu_submenu_mapping
(
    record_id serial primary key,
    fk_office_type_id integer,
    fk_eba_name_id integer,
    menu_id integer,
    sub_menu_id integer,
    display_sequence integer,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);

CREATE TABLE user_roles
(
    record_id serial primary key,
    fk_office_type_id integer,
    fk_eba_name_id integer,
    role_name character varying(255) COLLATE pg_catalog."default",
    role_description character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    created_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    is_active boolean DEFAULT true
);

CREATE TABLE user_roles_mapping
(
    record_id serial primary key,
    fk_office_type_id integer,
    fk_eba_name_id integer,
    user_record_id integer NOT NULL,
    roles_record_id integer NOT NULL,
	user_office_id integer,
    user_office_value character varying(100) COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true,
    CONSTRAINT fk_role_id FOREIGN KEY (roles_record_id)
        REFERENCES public.user_roles (record_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_id FOREIGN KEY (user_record_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE user_roles_menu_privileges_mapping
(
    record_id serial primary key,
    fk_office_type_id integer,
    fk_eba_name_id integer,
    fk_role_id integer,
    fk_menu_id integer,
    fk_submenu_id integer,
    data_json jsonb,
    display_sequence integer,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone,
    is_active boolean DEFAULT true
);
--table_id_generation_counter
INSERT INTO table_id_generation_counter(
	id, table_name, id_counter)VALUES
        (1,'users', 1);