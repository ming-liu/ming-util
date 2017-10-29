ALTER TABLE GDCity add `status` int unsigned NOT NULL DEFAULT 0 COMMENT '0,未开通;1已开通';
UPDATE GDCity set level = 5;
UPDATE GDCity SET level = 0,status = 0 where name in ('中华人民共和国','外国');
UPDATE GDCity SET level = 1,status = 0 where name like '%省';
UPDATE GDCity SET level = 1,status = 0 where name like '%自治区';
UPDATE GDCity SET level = 1,status = 1 where name in ('北京市','上海市','天津市','重庆市');
UPDATE GDCity SET level = 1,status = 1 where name like '%行政区';
UPDATE GDCity SET level = 3 ,status = 1 where adcode like '%00' and level <> 0 and level <> 1 and name not like '%市辖区';
UPDATE GDCity SET level = 2 ,status = 1 where name in ('深圳市','广州市','厦门市','杭州市','宁波市','成都市','南京市','长春市','沈阳市','大连市','哈尔滨市','济南市','青岛市','武汉市','西安市');

UPDATE GDCity SET level = 4 ,status = 0 where citycode in ('010','022','021','023','1852','1853') and level <> 1 and name like '%区';
UPDATE GDCity SET level = 4 ,status = 1 where citycode in ('010','022','021','023','1852','1853') and level <> 1 and name not like '%区';
UPDATE GDCity SET level = 4 ,status = 1 where level = 5 and name like '%县';
UPDATE GDCity SET level = 4 ,status = 1 where level = 5 and name like '%市';
UPDATE GDCity SET level = 4 ,status = 1 where name in ('文登区','从化区','增城区','双城区','鹿泉区','藁城区','九台区','富阳区','日喀则区','大丰区','金坛区','普兰店区','吐鲁番区','高要区','兖州区','哈密区','冀州区','章丘区','奉化区','宜州区','姜堰区','临安区','即墨区');
UPDATE GDCity SET level = 4 ,status = 1 where  level = 5 and name = '井陉矿区';
UPDATE GDCity SET level = 4 ,status = 1 where name like '%旗' and level = 5 ;

-- level 
-- 0:国家;
-- 1:省,省级市,自治区,行政区 ,可用4+2
-- 2:副省级市 15个
-- 3:地级行政区 334,294个地级市,30个自治州,7个地区,3个盟, 包含了副省级市
-- 4:直辖市、行政区下行政区,县,市
-- 5:其他


-- 省级城市6个,4个直辖市+港澳
-- 副省级市15个,深圳市、广州市、厦门市、杭州市、宁波市、成都市、南京市、长春市、沈阳市、大连市、哈尔滨市、济南市、青岛市、武汉市、西安市
-- 地级市（268个）
-- 撤市设区 '文登','从化','增城','双城','鹿泉','藁城','九台','富阳','日喀则','大丰','金坛','普兰店','吐鲁番','高要','兖州','哈密','冀州','章丘','奉化','宜州','姜堰','临安','即墨'