delete from item_note;
delete from item;
INSERT INTO item VALUES
    (1,"Red gloves","Fuzzy red gloves made in Wisconsin","Gloves/Mittens"),
    (2,"Blue gloves","Fuzzy blue gloves made in Wisconsin","Gloves/Mittens"),
    (3,"Grey sweater","A cashmere sweater made in Italy","Sweaters"),
    (4,"Yellow boots","Cozy yellow boots made in Wisconsin","Footwear"),
    (5,"Snowpants","Very warm snowpants made for the extreme Wisconsin winters","Pants");
INSERT INTO item_note VALUES
    (1, "Kind of itchy.", 1, NOW()),
    (2, "So comfy", 3, NOW()),
    (3, "Warm", 5, NOW()),
    (4, "These are cool", 4, NOW()),
    (5, "Wear these a lot", 1, NOW());








