create schema upvotes;

create table upvotes.usuarios (
    id bigserial not null primary key,
    nome character varying(200) not null,
    email character varying(100) not null,
    senha character varying(100) not null,
    dh_criacao timestamp not null default CURRENT_TIMESTAMP
);

create table upvotes.postagens (
    id bigserial not null primary key,
    titulo character varying(200) not null,
    conteudo character varying(1000) not null,
    i_usuarios bigserial not null references upvotes.usuarios(id),
    dh_criacao timestamp not null default CURRENT_TIMESTAMP
);

create table upvotes.postagens_votos (
    id bigserial not null primary key,
    i_postagens bigserial not null references upvotes.postagens(id),
    i_usuarios bigserial not null references upvotes.usuarios(id),
    tipo character varying(4) not null,
    dh_criacao timestamp not null default CURRENT_TIMESTAMP,
    constraint postagens_votos_tipo_check check (tipo::text = any (array['UP'::character varying, 'DOWN'::character varying]::text[]))
);