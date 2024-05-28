create table public.ticket
(
    ticket_id           uuid not null
        constraint ticket_pk
            primary key,
    requestor_name      varchar(100),
    requestor_email     varchar(100),
    description         varchar(256),
    status              varchar(25),
    created_at          timestamp
);