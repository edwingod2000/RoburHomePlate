--------------------------------------------------------
--  DDL for View BSM_TEAMBEHEERDERS
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_TEAMBEHEERDERS" AS
  select rownum as volgnr
       , tgbr.tem_volgnr as tem_volgnr
       , gbr.voornaam as voornaam
       , gbr.tussenvoegsel as tussenvoegsel
       , gbr.achternaam as naam
       , gbr.gebruikersid as gebruikersid
    from bsm_tem_gbr tgbr
       , gebruikers gbr
   where tgbr.gbr_volgnr = gbr.volgnr;

--------------------------------------------------------
--  DDL for View BSM_UPDATE_INFO
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_UPDATE_INFO" AS
   select tio.omschrijving      as omschrijving
        , tio.omschrijving_en   as omschrijving_en
        , tio.laatste_update    as datum
     from type_info tio
    where tio.volgnr in (select distinct fnc.type_info_volgnr
                           from bsm_functies fnc )
  union
   select decode(svr.code, 'LAATSTE_UPDATE_WED', 'Wedstrijden',
          'LAATSTE_UPDATE_DOC', 'Documenten',
          'LAATSTE_UPDATE_GAS', 'Gastenboek') as omschrijving
        , decode(svr.code, 'LAATSTE_UPDATE_WED', 'Games',
          'LAATSTE_UPDATE_DOC', 'Documents',
          'LAATSTE_UPDATE_GAS', 'Guestbook') as omschrijving
        , to_date(svr.waarde, 'DD-MM-YYYY') as datum
     from bsm_sysvar svr
    where code like 'LAATSTE%'
      and code <> 'LAATSTE_UPDATE_LIN'
  order by datum desc;

--------------------------------------------------------
--  DDL for View BSM_V_DOCUMENTEN
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_V_DOCUMENTEN" AS 
  select i.dct_volgnr
       , i.datum
       , i.datum_opname
       , i.info
       , doc.name
       , doc.mime_type
       , doc.doc_size
       , doc.dad_charset
       , doc.last_updated
       , doc.content_type
       , doc.content
       , doc.blob_content
       , s.dce_volgnr
  from bsm_doc_info i
     , bsm_doc_dce  s
     , bsm_documenten doc
 where i.dct_volgnr = s.doc_volgnr
   and i.naam = doc.name;

--------------------------------------------------------
--  DDL for View BSM_V_GASTENBOEK_JAAR
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_V_GASTENBOEK_JAAR" AS
  select distinct to_char(gbk.datum, 'YYYY') jaar
    from gastenboek gbk
   where gbk.type_gastenboek = 1
   order by 1;

--------------------------------------------------------
--  DDL for View BSM_V_GASTENBOEK_JAAR_2
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_V_GASTENBOEK_JAAR_2" AS
  select distinct to_char(gbk.datum, 'YYYY') jaar
    from gastenboek gbk
   where gbk.type_gastenboek = 2
   order by 1;

--------------------------------------------------------
--  DDL for View BSM_V_TEAM_BEHEERDERS
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_V_TEAM_BEHEERDERS" AS
  select distinct  t.volgnr as tem_volgnr, g.volgnr as gbr_volgnr 
    from bsm_tem_gbr tg
       , gebruikers g
       , teams t
   where g.volgnr = tg.gbr_volgnr
     and t.volgnr = tg.tem_volgnr;

--------------------------------------------------------
--  DDL for View BSM_WEDSTRIJDEN_INCL_SERIES
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_WEDSTRIJDEN_INCL_SERIES" AS
  SELECT wed.volgnr
       , wed.datum            AS datum
       , wed.thuis_uit        AS thuis_uit 
       , wed.tegenstander     AS tegenstander 
       , wed.tijdstip         AS tijdstip 
       , wed.wedstrijd_nummer AS wedstrijd_nummer 
       , wed.team_naam        AS team_naam 
       , wed.opmerking        AS opmerking 
       , wed.type_wedstrijd   AS type_wedstrijd 
       , wed.scheidsrechters  AS scheidsrechters 
       , wed.soort            AS soort 
       , wed.afgelast         AS afgelast
       , wed.veld_info        AS veld_info
       , tem.naam             AS naam
       , sls.omschrijving     AS veld_omschrijving
    FROM wedstrijden wed 
       , teams tem 
       , bsm_speellokaties sls
  WHERE wed.team_volgnr = tem.volgnr(+)
  AND wed.datum         < TRUNC(sysdate + 7)
  AND wed.datum        >= TRUNC(sysdate - 1)
  AND wed.archief       = 'N'
  AND wed.veld_info     = sls.volgnr(+)
  UNION
  SELECT wed1.volgnr ,
    wed1.datum                     AS datum ,
    'T'                            AS thuis_uit ,
    wed1.display_uit               AS tegenstander ,
    wed1.tijdstip                  AS tijdstip ,
    TO_CHAR(wed1.wedstrijd_nummer) AS wedstrijd_nummer ,
    NULL                           AS team_naam ,
    wed1.opmerking                 AS opmerking ,
    wed1.type_wedstrijd            AS type_wedstrijd ,
    wed1.scheidsrechters           AS scheidsrechters ,
    wed1.soort                     AS soort ,
    'N'                            AS afgelast ,
    NULL                           AS veld_info ,
    wed1.display_thuis             AS naam ,
    NULL                           AS veld_omschrijving
  FROM ris_v_wedstrijden wed1
  WHERE wed1.datum < TRUNC(sysdate + 7)
  AND wed1.datum  >= TRUNC(sysdate - 1)
  ORDER BY datum ,
    soort ,
    tijdstip;

--------------------------------------------------------
--  DDL for View BSM_WEDSTRIJDEN_TOTAAL
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BSM_WEDSTRIJDEN_TOTAAL" AS
    select wed.volgnr
            ,wed.datum as datum
            ,wed.thuis_uit as thuis_uit
            ,wed.tegenstander as tegenstander
            ,wed.tijdstip as tijdstip
            ,wed.wedstrijd_nummer as wedstrijd_nummer
            ,wed.team_naam as team_naam
            ,wed.opmerking as opmerking
            ,wed.type_wedstrijd as type_wedstrijd
            ,wed.scheidsrechters as scheidsrechters
            ,wed.soort as soort
            ,wed.afgelast as afgelast
            ,wed.veld_info as veld_info
            ,tem.naam as naam
        from wedstrijden wed
            ,teams tem
       where wed.team_volgnr = tem.volgnr(+)
         and wed.datum >= trunc(sysdate)
         and wed.archief = 'N'
      union
      select wed1.volgnr
            ,wed1.datum as datum
            ,'T' as thuis_uit
            ,wed1.display_uit as tegenstander
            ,wed1.tijdstip as tijdstip
            ,to_char(wed1.wedstrijd_nummer) as wedstrijd_nummer
            ,null as team_naam
            ,wed1.opmerking as opmerking
            ,wed1.type_wedstrijd as type_wedstrijd
            ,wed1.scheidsrechters as scheidsrechters
            ,wed1.soort as soort
            ,'N' as afgelast
            ,null as veld_info
            ,wed1.display_thuis as naam
        from ris_v_wedstrijden wed1
       where wed1.datum >= trunc(sysdate)
       order by datum
               ,soort
               ,tijdstip
;
