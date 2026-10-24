-- Write your PostgreSQL query statement below
with words as (
    select content_id,content_text, string_to_table (content_text,' ') as word from user_content
)
select 
    content_id,
    content_text as original_text,
    String_agg((
        case when substr(word,1,1) ~ '[a-zA-Z]' then 
            case when regexp_count(word,'-') = 1 then upper(substr(split_part(word,'-',1),1,1))||lower(substr(split_part(word,'-',1),2))||'-'||upper(substr(split_part(word,'-',2),1,1))||lower(substr(split_part(word,'-',2),2))
                 when regexp_count(word,'-') = 0 then upper(substr(word,1,1))||lower(substr(word,2))
                 when regexp_count(word,'-') > 1 then 
                    case when word ~ '[^a-zA-Z0-9]{2}' then upper(substr(word,1,1))||lower(substr(word,2))
                            else initcap(word)
                    end 
                 else word
            end
            else word
        end
    ),' ') as converted_text
    from words group by content_id,content_text