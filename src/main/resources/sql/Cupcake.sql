--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-09 09:59:56 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 18150)
-- Name: bottom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottom (
    bottom_id bigint NOT NULL,
    bottom_number integer,
    bottom_name character varying,
    bottom_price double precision
);


ALTER TABLE public.bottom OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 18149)
-- Name: bottom_bottom_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bottom_bottom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bottom_bottom_id_seq OWNER TO postgres;

--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 223
-- Name: bottom_bottom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bottom_bottom_id_seq OWNED BY public.bottom.bottom_id;


--
-- TOC entry 216 (class 1259 OID 18118)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    order_id bigint NOT NULL,
    order_number integer,
    user_number integer,
    order_price double precision,
    order_date date,
    order_pickuptime timestamp without time zone
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 18117)
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.order_order_id_seq OWNER TO postgres;

--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 215
-- Name: order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_order_id_seq OWNED BY public."order".order_id;


--
-- TOC entry 220 (class 1259 OID 18134)
-- Name: orderline; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderline (
    orderline_id bigint NOT NULL,
    user_number integer,
    topping_number character varying,
    bottom_number character varying,
    quantity integer,
    order_price double precision,
    orderline_date date,
    orderline_number integer
);


ALTER TABLE public.orderline OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 18133)
-- Name: orderline_orderline_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderline_orderline_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orderline_orderline_id_seq OWNER TO postgres;

--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 219
-- Name: orderline_orderline_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orderline_orderline_id_seq OWNED BY public.orderline.orderline_id;


--
-- TOC entry 222 (class 1259 OID 18143)
-- Name: topping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topping (
    topping_id bigint NOT NULL,
    topping_number integer,
    topping_name character varying(30),
    topping_price double precision
);


ALTER TABLE public.topping OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 18142)
-- Name: topping_topping_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topping_topping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.topping_topping_id_seq OWNER TO postgres;

--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 221
-- Name: topping_topping_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topping_topping_id_seq OWNED BY public.topping.topping_id;


--
-- TOC entry 218 (class 1259 OID 18125)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_number integer,
    user_email character varying,
    username character varying,
    password character varying,
    role character varying,
    balance double precision
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 18124)
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_user_id_seq OWNER TO postgres;

--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;


--
-- TOC entry 3227 (class 2604 OID 18153)
-- Name: bottom bottom_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom ALTER COLUMN bottom_id SET DEFAULT nextval('public.bottom_bottom_id_seq'::regclass);


--
-- TOC entry 3223 (class 2604 OID 18121)
-- Name: order order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN order_id SET DEFAULT nextval('public.order_order_id_seq'::regclass);


--
-- TOC entry 3225 (class 2604 OID 18137)
-- Name: orderline orderline_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline ALTER COLUMN orderline_id SET DEFAULT nextval('public.orderline_orderline_id_seq'::regclass);


--
-- TOC entry 3226 (class 2604 OID 18146)
-- Name: topping topping_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping ALTER COLUMN topping_id SET DEFAULT nextval('public.topping_topping_id_seq'::regclass);


--
-- TOC entry 3224 (class 2604 OID 18128)
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);


--
-- TOC entry 3399 (class 0 OID 18150)
-- Dependencies: 224
-- Data for Name: bottom; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bottom VALUES (1, 1, 'Chocolate', 5);
INSERT INTO public.bottom VALUES (2, 2, 'Vanilla', 5);
INSERT INTO public.bottom VALUES (3, 3, 'Nutmeg', 5);
INSERT INTO public.bottom VALUES (4, 4, 'Pistacio', 6);
INSERT INTO public.bottom VALUES (5, 5, 'Almond', 7);


--
-- TOC entry 3391 (class 0 OID 18118)
-- Dependencies: 216
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3395 (class 0 OID 18134)
-- Dependencies: 220
-- Data for Name: orderline; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3397 (class 0 OID 18143)
-- Dependencies: 222
-- Data for Name: topping; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topping VALUES (1, 1, 'Chocolate', 5);
INSERT INTO public.topping VALUES (2, 2, 'Blueberry', 5);
INSERT INTO public.topping VALUES (3, 3, 'Rasberry', 5);
INSERT INTO public.topping VALUES (4, 4, 'Crispy', 6);
INSERT INTO public.topping VALUES (5, 5, 'Strawberry', 6);
INSERT INTO public.topping VALUES (6, 6, 'Rum/Raisin', 7);
INSERT INTO public.topping VALUES (7, 7, 'Orange', 8);
INSERT INTO public.topping VALUES (8, 8, 'Lemon', 8);
INSERT INTO public.topping VALUES (9, 9, 'Blue cheese', 9);


--
-- TOC entry 3393 (class 0 OID 18125)
-- Dependencies: 218
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES (1, NULL, 'maria.noermoelle@hotmail.com', 'Maria', '1234', 'user', 400);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 223
-- Name: bottom_bottom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bottom_bottom_id_seq', 5, true);


--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 215
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_order_id_seq', 1, false);


--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 219
-- Name: orderline_orderline_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderline_orderline_id_seq', 1, false);


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 221
-- Name: topping_topping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.topping_topping_id_seq', 9, true);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_user_id_seq', 1, true);


--
-- TOC entry 3243 (class 2606 OID 18157)
-- Name: bottom bottom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY (bottom_id);


--
-- TOC entry 3229 (class 2606 OID 18175)
-- Name: order order_order_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_order_number_key UNIQUE (order_number);


--
-- TOC entry 3231 (class 2606 OID 18123)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3233 (class 2606 OID 18163)
-- Name: order order_user_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_user_number_key UNIQUE (user_number);


--
-- TOC entry 3239 (class 2606 OID 18141)
-- Name: orderline orderline_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT orderline_pkey PRIMARY KEY (orderline_id);


--
-- TOC entry 3241 (class 2606 OID 18148)
-- Name: topping topping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping
    ADD CONSTRAINT topping_pkey PRIMARY KEY (topping_id);


--
-- TOC entry 3235 (class 2606 OID 18132)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3237 (class 2606 OID 18161)
-- Name: user user_user_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_user_number_key UNIQUE (user_number);


--
-- TOC entry 3244 (class 2606 OID 18164)
-- Name: order fk_order_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk_order_user FOREIGN KEY (user_number) REFERENCES public."user"(user_number) NOT VALID;


--
-- TOC entry 3245 (class 2606 OID 18176)
-- Name: orderline fk_orderline_order; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT fk_orderline_order FOREIGN KEY (orderline_number) REFERENCES public."order"(order_number) NOT VALID;


--
-- TOC entry 3246 (class 2606 OID 18169)
-- Name: orderline fk_orderline_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT fk_orderline_user FOREIGN KEY (user_number) REFERENCES public."user"(user_number) NOT VALID;


-- Completed on 2024-04-09 09:59:56 UTC

--
-- PostgreSQL database dump complete
--

