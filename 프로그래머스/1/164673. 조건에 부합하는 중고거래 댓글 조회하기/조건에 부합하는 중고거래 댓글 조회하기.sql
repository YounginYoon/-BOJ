-- 코드를 입력하세요
select B.TITLE, 
    B.BOARD_ID, 
    R.REPLY_ID, 
    R.WRITER_ID, 
    R.CONTENTS, 
    date_format(R.CREATED_DATE, '%Y-%m-%d')
from USED_GOODS_BOARD as B, USED_GOODS_REPLY as R
where B.BOARD_ID = R.BOARD_ID
    and date_format(B.CREATED_DATE, '%Y-%m') = '2022-10'
order by R.CREATED_DATE, B.TITLE;