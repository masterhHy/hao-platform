/*
Navicat PGSQL Data Transfer

Source Server         : ali
Source Server Version : 90614
Source Host           : 120.24.86.195:5432
Source Database       : hy_platform
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90614
File Encoding         : 65001

Date: 2020-01-11 16:24:08
*/


-- ----------------------------
-- Sequence structure for fn_fund_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_fund_code_id_seq";
CREATE SEQUENCE "public"."fn_fund_code_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1154
 CACHE 1;
SELECT setval('"public"."fn_fund_code_id_seq"', 1154, true);

-- ----------------------------
-- Sequence structure for fn_fund_holdings_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_fund_holdings_id_seq";
CREATE SEQUENCE "public"."fn_fund_holdings_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 503405
 CACHE 1;
SELECT setval('"public"."fn_fund_holdings_id_seq"', 503405, true);

-- ----------------------------
-- Sequence structure for fn_fund_ranking_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_fund_ranking_id_seq";
CREATE SEQUENCE "public"."fn_fund_ranking_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1154
 CACHE 1;
SELECT setval('"public"."fn_fund_ranking_id_seq"', 1154, true);

-- ----------------------------
-- Sequence structure for fn_rise_params_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_rise_params_id_seq";
CREATE SEQUENCE "public"."fn_rise_params_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."fn_rise_params_id_seq"', 1, true);

-- ----------------------------
-- Sequence structure for fn_rise_stock_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_rise_stock_code_id_seq";
CREATE SEQUENCE "public"."fn_rise_stock_code_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 929
 CACHE 1;
SELECT setval('"public"."fn_rise_stock_code_id_seq"', 929, true);

-- ----------------------------
-- Sequence structure for fn_stock_code_day_data_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_stock_code_day_data_id_seq";
CREATE SEQUENCE "public"."fn_stock_code_day_data_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4400759
 CACHE 1;
SELECT setval('"public"."fn_stock_code_day_data_id_seq"', 4400759, true);

-- ----------------------------
-- Sequence structure for fn_stock_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_stock_code_id_seq";
CREATE SEQUENCE "public"."fn_stock_code_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 15476
 CACHE 1;
SELECT setval('"public"."fn_stock_code_id_seq"', 15476, true);

-- ----------------------------
-- Sequence structure for fn_stock_code_investor_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fn_stock_code_investor_id_seq";
CREATE SEQUENCE "public"."fn_stock_code_investor_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3169307
 CACHE 1;
SELECT setval('"public"."fn_stock_code_investor_id_seq"', 3169307, true);

-- ----------------------------
-- Table structure for fn_fund_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_fund_code";
CREATE TABLE "public"."fn_fund_code" (
"id" int4 DEFAULT nextval('fn_fund_code_id_seq'::regclass) NOT NULL,
"code" varchar(6) COLLATE "default" NOT NULL,
"name" varchar(20) COLLATE "default" NOT NULL,
"type" int2 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_fund_code"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_fund_code"."code" IS '基金代码';
COMMENT ON COLUMN "public"."fn_fund_code"."name" IS '基金名称';
COMMENT ON COLUMN "public"."fn_fund_code"."type" IS '基金类型1 股票型 2 混合型';
COMMENT ON COLUMN "public"."fn_fund_code"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_fund_code"."update_time" IS '更新时间';

-- ----------------------------
-- Table structure for fn_fund_holdings
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_fund_holdings";
CREATE TABLE "public"."fn_fund_holdings" (
"id" int4 DEFAULT nextval('fn_fund_holdings_id_seq'::regclass) NOT NULL,
"fund_code_id" int4 NOT NULL,
"stock_code_id" int4 NOT NULL,
"day" varchar(19) COLLATE "default" NOT NULL,
"percent" numeric(5,2) NOT NULL,
"stock_num" numeric(10,2) NOT NULL,
"stock_worth" numeric(10,2) NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_fund_holdings"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_fund_holdings"."fund_code_id" IS '基金id';
COMMENT ON COLUMN "public"."fn_fund_holdings"."stock_code_id" IS '股票id';
COMMENT ON COLUMN "public"."fn_fund_holdings"."day" IS '公布时间';
COMMENT ON COLUMN "public"."fn_fund_holdings"."percent" IS '持股占比';
COMMENT ON COLUMN "public"."fn_fund_holdings"."stock_num" IS '股票数量';
COMMENT ON COLUMN "public"."fn_fund_holdings"."stock_worth" IS '股票总值';
COMMENT ON COLUMN "public"."fn_fund_holdings"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_fund_holdings"."update_time" IS '更新时间';

-- ----------------------------
-- Table structure for fn_fund_ranking
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_fund_ranking";
CREATE TABLE "public"."fn_fund_ranking" (
"id" int4 DEFAULT nextval('fn_fund_ranking_id_seq'::regclass) NOT NULL,
"fund_code_id" int4 NOT NULL,
"worth" numeric(5,2) NOT NULL,
"one_day" numeric(5,2) NOT NULL,
"one_week" numeric(5,2) NOT NULL,
"one_month" numeric(5,2) NOT NULL,
"three_month" numeric(5,2) NOT NULL,
"six_month" numeric(5,2) NOT NULL,
"one_year" numeric(5,2) NOT NULL,
"create_time" timestamp(0) NOT NULL,
"update_time" timestamp(0)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_fund_ranking"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_fund_ranking"."fund_code_id" IS '基金代码';
COMMENT ON COLUMN "public"."fn_fund_ranking"."worth" IS '基金净值';
COMMENT ON COLUMN "public"."fn_fund_ranking"."one_day" IS '一天累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."one_week" IS '一周累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."one_month" IS '一个月累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."three_month" IS '三个月累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."six_month" IS '六个月累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."one_year" IS '一年累计涨幅';
COMMENT ON COLUMN "public"."fn_fund_ranking"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_fund_ranking"."update_time" IS '更新时间';

-- ----------------------------
-- Table structure for fn_rise_params
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_rise_params";
CREATE TABLE "public"."fn_rise_params" (
"id" int4 DEFAULT nextval('fn_rise_params_id_seq'::regclass) NOT NULL,
"r2" numeric(5,4) NOT NULL,
"days" int2 NOT NULL,
"beta" numeric(5,2) NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6),
"status" int2
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_rise_params"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_rise_params"."r2" IS '方差';
COMMENT ON COLUMN "public"."fn_rise_params"."days" IS '天数最小值';
COMMENT ON COLUMN "public"."fn_rise_params"."beta" IS '斜率最小值';
COMMENT ON COLUMN "public"."fn_rise_params"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_rise_params"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."fn_rise_params"."status" IS '是否生效 0 不生效 1 生效';

-- ----------------------------
-- Table structure for fn_rise_stock_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_rise_stock_code";
CREATE TABLE "public"."fn_rise_stock_code" (
"id" int4 DEFAULT nextval('fn_rise_stock_code_id_seq'::regclass) NOT NULL,
"stock_code_id" int4 NOT NULL,
"rise_param_id" int4 NOT NULL,
"days" int2 NOT NULL,
"beta" numeric(5,2) NOT NULL,
"r2" numeric(5,4) NOT NULL,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_rise_stock_code"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."stock_code_id" IS '股票id';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."rise_param_id" IS '汇聚参数方案id';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."days" IS '最大天数';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."beta" IS '斜率';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."r2" IS '方差';
COMMENT ON COLUMN "public"."fn_rise_stock_code"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for fn_stock_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_stock_code";
CREATE TABLE "public"."fn_stock_code" (
"id" int4 DEFAULT nextval('fn_stock_code_id_seq'::regclass) NOT NULL,
"code" varchar(6) COLLATE "default" NOT NULL,
"name" varchar(10) COLLATE "default" NOT NULL,
"type" int4 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6),
"industry" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_stock_code"."code" IS '股票代码';
COMMENT ON COLUMN "public"."fn_stock_code"."name" IS '股票名称';
COMMENT ON COLUMN "public"."fn_stock_code"."type" IS '股票类型';
COMMENT ON COLUMN "public"."fn_stock_code"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_stock_code"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."fn_stock_code"."industry" IS '股票行业';

-- ----------------------------
-- Table structure for fn_stock_code_day_data
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_stock_code_day_data";
CREATE TABLE "public"."fn_stock_code_day_data" (
"id" int4 DEFAULT nextval('fn_stock_code_day_data_id_seq'::regclass) NOT NULL,
"open_date" varchar(20) COLLATE "default" NOT NULL,
"open_price" numeric(10,2) NOT NULL,
"close_price" numeric(10,2) NOT NULL,
"top_price" numeric(10,2) NOT NULL,
"low_price" numeric(10,2) NOT NULL,
"final_percent" numeric(5,2) NOT NULL,
"num" numeric(10,2) NOT NULL,
"amount" numeric(10,2) NOT NULL,
"change_hand" numeric(5,2) NOT NULL,
"stock_code_id" int4 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."open_date" IS '开盘日期';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."open_price" IS '开盘价格';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."close_price" IS '收盘价格';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."top_price" IS '最高价格';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."low_price" IS '最低价格';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."final_percent" IS '涨跌幅';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."num" IS '成交量(手)';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."amount" IS '成交金额(亿)';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."change_hand" IS '换手率';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."stock_code_id" IS '股票id';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."fn_stock_code_day_data"."update_time" IS '修改时间';

-- ----------------------------
-- Table structure for fn_stock_code_investor
-- ----------------------------
DROP TABLE IF EXISTS "public"."fn_stock_code_investor";
CREATE TABLE "public"."fn_stock_code_investor" (
"id" int4 DEFAULT nextval('fn_stock_code_investor_id_seq'::regclass) NOT NULL,
"stock_code_id" int4 NOT NULL,
"investor_name" varchar(100) COLLATE "default" NOT NULL,
"invertor_type" int2 NOT NULL,
"day" varchar(19) COLLATE "default" NOT NULL,
"percent" numeric(5,2) NOT NULL,
"stock_num" numeric(10,2) NOT NULL,
"stock_worth" numeric(10,2) NOT NULL,
"comment" varchar(10) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."fn_stock_code_investor"."id" IS '表id';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."stock_code_id" IS '股票id';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."investor_name" IS '投资者名称';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."invertor_type" IS '机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."day" IS '公布时间';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."percent" IS '持股占比';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."stock_num" IS '股票数量';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."stock_worth" IS '股票总值';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."comment" IS '增持情况';
COMMENT ON COLUMN "public"."fn_stock_code_investor"."create_time" IS '创建时间';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."fn_fund_code_id_seq" OWNED BY "fn_fund_code"."id";
ALTER SEQUENCE "public"."fn_fund_holdings_id_seq" OWNED BY "fn_fund_holdings"."id";
ALTER SEQUENCE "public"."fn_fund_ranking_id_seq" OWNED BY "fn_fund_ranking"."id";
ALTER SEQUENCE "public"."fn_rise_stock_code_id_seq" OWNED BY "fn_rise_stock_code"."id";
ALTER SEQUENCE "public"."fn_stock_code_day_data_id_seq" OWNED BY "fn_stock_code_day_data"."id";
ALTER SEQUENCE "public"."fn_stock_code_id_seq" OWNED BY "fn_stock_code"."id";
ALTER SEQUENCE "public"."fn_stock_code_investor_id_seq" OWNED BY "fn_stock_code_investor"."id";

-- ----------------------------
-- Primary Key structure for table fn_fund_code
-- ----------------------------
ALTER TABLE "public"."fn_fund_code" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table fn_fund_holdings
-- ----------------------------
ALTER TABLE "public"."fn_fund_holdings" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table fn_fund_ranking
-- ----------------------------
ALTER TABLE "public"."fn_fund_ranking" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table fn_stock_code_investor
-- ----------------------------
CREATE INDEX "idx_investor_day" ON "public"."fn_stock_code_investor" USING btree ("day");

-- ----------------------------
-- Primary Key structure for table fn_stock_code_investor
-- ----------------------------
ALTER TABLE "public"."fn_stock_code_investor" ADD PRIMARY KEY ("id");
