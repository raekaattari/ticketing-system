create table public.comment
(
    comment_id          uuid not null
        constraint comment_id_pk
            primary key,
    ticket_id            uuid
        constraint comment_ticket_ticket_id_fk
            references ticket,
    comment_text        varchar(1024),
    created_at          timestamp
);