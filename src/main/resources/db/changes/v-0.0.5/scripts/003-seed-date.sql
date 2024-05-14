INSERT INTO public.user_info (id, login, password, first_name, second_name, patronymic, role, is_deleted) VALUES (1, 'Vasko.KA', '$2a$10$0V6ZkQOQHrixsYAr4y2dounSwPpoFi3wPO73gdRfxE8W1s9Sf/BKm', 'Кирилл', 'Васько', 'Андреевич', 0, false);
INSERT INTO public.user_info (id, login, password, first_name, second_name, patronymic, role, is_deleted) VALUES (2, 'Mahanko.MV', '$2a$10$ISvftJz8J3QccowgFFhDbuP3KhIkv8gurXuomah5peCFgui.dX4gW', 'Максим', 'Маханько', 'Витальевич', 0, false);
INSERT INTO public.referee (id, user_info_id, category, is_deleted) VALUES (1, 1, 0, false);
INSERT INTO public.referee (id, user_info_id, category, is_deleted) VALUES (2, 2, 0, false);

INSERT INTO public.user_info (id, login, password, first_name, second_name, patronymic, role, is_deleted) VALUES (3, null, null, 'Максим', 'Маханько', 'Витальевич', 2, false);
INSERT INTO public.skydiver (id, gender, birth_date, place_of_birth, place_of_work, education, phone_number, couch_name, height, weight, shoe_size, jacket_size, pants_size, begin_of_sport_career, sport_specialization, sport_degree, jumping_amount, is_deleted, is_internal) VALUES (3, 0, '2002-12-11 00:00:00.000000', 'город Берёзовка', 'ООО Модсен', 'Среднее', '+375259373579', null, null, null, null, null, null, null, null, null, 0, false, true);
INSERT INTO public.passport_info (id, skydiver_id, series, number, personal_number, issuing_authority, issued_date) VALUES (2, 3, 'KH', '1234567', '12345678910121', 'Лидский РОВД Гродненской области', '2013-12-11 00:00:00.000000');

INSERT INTO public.competition (id, name, begin_date, end_date, address, status, number_of_stages, is_deleted) VALUES (1, 'Гродненские региональные соревнования', '2024-05-14 00:00:00.000000', '2024-05-24 00:00:00.000000', 'Гродно, ул. Белые Росы 57, кв 7', 1, 2, false);
INSERT INTO public.competition_stage (id, competition_id, number) VALUES (1, 1, 1);
INSERT INTO public.competition_stage_referee_trans (id, competition_stage_id, referee_id, work_performed, is_main_collegium) VALUES (1, 1, 1, 'Очень важная', true);
INSERT INTO public.competition_stage_referee_trans (id, competition_stage_id, referee_id, work_performed, is_main_collegium) VALUES (2, 1, 2, 'Не очень важная', false);
