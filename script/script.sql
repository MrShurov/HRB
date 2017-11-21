create table bet
(
	ID bigint auto_increment
		primary key,
	BET double null,
	COEFFICIENT double null,
	VALUE_BET varchar(255) null,
	event_EVENT_ID bigint null,
	user_ID bigint null
)
;

create index FK1ghnqte9se1dmurdkj8k6ncuw
	on bet (user_ID)
;

create index FK4be864jw19c84dwrxsvxjh3ta
	on bet (event_EVENT_ID)
;

create table event
(
	EVENT_ID bigint auto_increment
		primary key,
	DRAW_COEFFICIENT double null,
	WIN1_COEFFICIENT double null,
	WIN2_COEFFICIENT double null
)
;

create table event_teams
(
	TEAM_ID bigint not null,
	EVENT_ID bigint not null
)
;

create index FK4spd6aqtiyn971wttt5agpss6
	on event_teams (EVENT_ID)
;

create index FKhr6h7swhopg25y0i7847fnl8m
	on event_teams (TEAM_ID)
;

create table info
(
	id bigint auto_increment
		primary key,
	BALANCE double null,
	ROLE varchar(255) null,
	STATUS varchar(255) null
)
;

create table team
(
	TEAM_ID bigint auto_increment
		primary key,
	LOSE_AMOUNT bigint null,
	NAME varchar(255) null,
	WIN_AMOUNT bigint null
)
;

create table team_event
(
	Team_TEAM_ID bigint not null,
	events_EVENT_ID bigint not null
)
;

create index FK9ympr39t9qhsuww341a4eu5e7
	on team_event (events_EVENT_ID)
;

create index FKhc6cvib6dt5xa0erudq54v8b
	on team_event (Team_TEAM_ID)
;

create table user
(
	ID bigint auto_increment
		primary key,
	PASSWORD varchar(255) null,
	USERNAME varchar(255) null,
	info_id bigint null
)
;

create index FKjki91b0cgc9x9h61uedp0c7cs
	on user (info_id)
;