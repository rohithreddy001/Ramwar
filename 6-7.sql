CREATE OR REPLACE FUNCTION tax_calc_sf
(p_id IN NUMBER)
RETURN bb_basket.subtotal%TYPE
IS
lv_state_txt bb_basket.shipstate%TYPE;
lv_subtot_num bb_basket.subtotal%TYPE;
lv_tax_num NUMBER := 0;
BEGIN
SELECT subtotal, COALESCE(shipstate,'NA') AS shipstate 
INTO lv_subtot_num, lv_state_txt
FROM bb_basket
WHERE idbasket = p_id;

IF lv_state_txt = 'VA' THEN
lv_tax_num := lv_subtot_num * .045;
ELSIF lv_state_txt = 'NC' THEN
lv_tax_num := lv_subtot_num * .03;
ELSIF lv_state_txt = 'SC' THEN
lv_tax_num := lv_subtot_num * .06;
ELSIF lv_state_txt = 'NA' THEN
lv_tax_num := 0;
END IF;
RETURN lv_tax_num;
EXCEPTION
WHEN NO_DATA_FOUND THEN
RETURN 0;
END;