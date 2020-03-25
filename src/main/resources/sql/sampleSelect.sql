-- SQL pobierający szczegóły posiłków w określonym planie
SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description
FROM `recipe_plan`
JOIN day_name on day_name.id=day_name_id
JOIN recipe on recipe.id=recipe_id WHERE plan_id = 6 -- zamiast 6 należy wstawić id planu do pobrania --
ORDER by day_name.display_order, recipe_plan.display_order;


-- SQL - pobiera najnowszy plan dla zadanego użytkownika (tabela admins)
SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description
FROM `recipe_plan`
JOIN day_name on day_name.id=day_name_id
JOIN recipe on recipe.id=recipe_id WHERE
recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = 1) -- zamiast 1 należy wstawić id użytkownika (tabela admins) --
ORDER by day_name.display_order, recipe_plan.display_order;

-- SQL - pobiera najnowszy plan dla zadenego użytkownika (tabela admins)
SELECT plan.name as name, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_plan.id
FROM recipe_plan
    JOIN plan on plan.id = plan_id
    JOIN day_name on day_name.id = day_name_id
    JOIN recipe on recipe.id = recipe_id
    WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = 17)
ORDER BY  day_name.display_order, recipe_plan.display_order;

-- SQL pobiera szczegoly planu  po id planu
SELECT plan.name as name, plan.description as description, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_plan.id as id, recipe.id as recipe_id
FROM recipe_plan
    JOIN plan on plan.id = plan_id
    JOIN day_name on day_name.id = day_name_id
    JOIN recipe on recipe.id = recipe_id
    WHERE plan_id = 6
ORDER BY day_name.display_order, recipe_plan.display_order;
#     WHERE plan_id = 6

SELECT plan.name as name, plan.description as description
FROM plan
    WHERE plan.id = (SELECT  MAX(id) FROM plan WHERE admin_id = 17);

SELECT * from recipe_plan where recipe_id = 18;

#return all
SELECT name FROM recipe WHERE name LIKE '%anyWord%';
#no duplication
SELECT DISTINCT name FROM recipe WHERE name LIKE '%bi%';