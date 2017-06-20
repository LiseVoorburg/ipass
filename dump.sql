--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

-- Started on 2017-06-20 10:25:32

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 190 (class 1259 OID 24590)
-- Name: af_id_aq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE af_id_aq
    START WITH 7
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE af_id_aq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 16451)
-- Name: afspraak; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE afspraak (
    id integer NOT NULL,
    datum character varying(255),
    gebruikersid integer,
    soort character varying(255),
    tijd character varying(255)
);


ALTER TABLE afspraak OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16443)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE employee (
    eid integer NOT NULL,
    deg character varying(255),
    ename character varying(255),
    salary double precision
);


ALTER TABLE employee OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16464)
-- Name: gebruiker; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE gebruiker (
    id integer NOT NULL,
    voornaam character varying(30) NOT NULL,
    achternaam character varying(30) NOT NULL,
    rol character varying(30) NOT NULL,
    wachtwoord character varying(30) NOT NULL
);


ALTER TABLE gebruiker OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16459)
-- Name: sequence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);


ALTER TABLE sequence OWNER TO postgres;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 190
-- Name: af_id_aq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('af_id_aq', 28, true);


--
-- TOC entry 2148 (class 0 OID 16451)
-- Dependencies: 187
-- Data for Name: afspraak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY afspraak (id, datum, gebruikersid, soort, tijd) FROM stdin;
3	24/06/2017	3	voetreflex 30 min	10:00
6	27/07/2017	3	voetreflex 60 min	11:00
5	24/06/2017	5	aromatherapie 60 min	14:00
15	2017-08-18	2	voetreflex 30 min	12:00
2	2017-08-18	2	voetreflex 30 min	12:00
20	2019-06-23	2	voetreflex 30 min	12:00
21	2018-05-31	4	voetreflex 60 min	14:00
22	2017-07-04	4	voetreflex 30 min	12:00
26		2	voetreflex 60 min	14:00
\.


--
-- TOC entry 2147 (class 0 OID 16443)
-- Dependencies: 186
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee (eid, deg, ename, salary) FROM stdin;
1201	Technical Manager	Gopal	40000
1202	Technical Manager	Gopa	40000
\.


--
-- TOC entry 2150 (class 0 OID 16464)
-- Dependencies: 189
-- Data for Name: gebruiker; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gebruiker (id, voornaam, achternaam, rol, wachtwoord) FROM stdin;
1	sandra	de boer	eigenaar	ale
2	roos	bloem	gebruiker	deurklink
3	tim	kast	gebruiker	madagascar
4	tom	deur	gebruiker	zeven
5	iris	pony	gebruiker	paardje
\.


--
-- TOC entry 2149 (class 0 OID 16459)
-- Dependencies: 188
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sequence (seq_name, seq_count) FROM stdin;
SEQ_GEN	0
\.


--
-- TOC entry 2026 (class 2606 OID 16478)
-- Name: gebruiker ID; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gebruiker
    ADD CONSTRAINT "ID" UNIQUE (id);


--
-- TOC entry 2019 (class 2606 OID 16476)
-- Name: afspraak ID_UN; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY afspraak
    ADD CONSTRAINT "ID_UN" UNIQUE (id);


--
-- TOC entry 2021 (class 2606 OID 16458)
-- Name: afspraak afspraak_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY afspraak
    ADD CONSTRAINT afspraak_pkey PRIMARY KEY (id);


--
-- TOC entry 2017 (class 2606 OID 16450)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (eid);


--
-- TOC entry 2028 (class 2606 OID 16468)
-- Name: gebruiker gebruiker_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gebruiker
    ADD CONSTRAINT gebruiker_pkey PRIMARY KEY (id);


--
-- TOC entry 2024 (class 2606 OID 16463)
-- Name: sequence sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);


--
-- TOC entry 2022 (class 1259 OID 16474)
-- Name: fki_ID_GID_FK; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_ID_GID_FK" ON afspraak USING btree (gebruikersid);


--
-- TOC entry 2029 (class 2606 OID 24585)
-- Name: afspraak ID_GID_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY afspraak
    ADD CONSTRAINT "ID_GID_FK" FOREIGN KEY (gebruikersid) REFERENCES gebruiker(id) ON DELETE CASCADE;


-- Completed on 2017-06-20 10:25:33

--
-- PostgreSQL database dump complete
--

