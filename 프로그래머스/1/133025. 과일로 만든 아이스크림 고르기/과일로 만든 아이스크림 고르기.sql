-- 코드를 입력하세요
select F.FLAVOR
from FIRST_HALF AS F, ICECREAM_INFO AS I
where F.FLAVOR = I.FLAVOR
    and F.TOTAL_ORDER >= 3000
    and I.INGREDIENT_TYPE='fruit_based'
order by F.TOTAL_ORDER desc;