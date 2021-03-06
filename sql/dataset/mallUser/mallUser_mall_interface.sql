insert into mallUser.mall_interface (inter_id, inter_name, inter_info, create_time, create_user, update_time, update_user) values (1, '/admin/**', '管理员权限', '2021-03-13 16:40:16', 0, '2021-03-16 21:43:18', 0);
insert into mallUser.mall_interface (inter_id, inter_name, inter_info, create_time, create_user, update_time, update_user) values (2, '/businessAdmin/**', '业务管理员权限', '2021-03-13 16:41:21', 0, '2021-04-18 12:16:27', 0);
insert into mallUser.mall_interface (inter_id, inter_name, inter_info, create_time, create_user, update_time, update_user) values (3, '/marketAdmin/**', '营销管理员权限', '2021-03-13 16:41:39', 0, '2021-04-18 12:16:27', 0);
insert into mallUser.mall_interface (inter_id, inter_name, inter_info, create_time, create_user, update_time, update_user) values (4, '/user/**', '用户访问权限', '2021-03-13 16:43:30', 0, '2021-03-16 21:43:18', 0);
insert into mallUser.mall_interface (inter_id, inter_name, inter_info, create_time, create_user, update_time, update_user) values (5, '/tourist/**', '游客访问权限', '2021-03-13 16:43:46', 0, '2021-03-16 21:43:18', 0);

insert into mallUser.mall_role (role_id, role_name, role_info, create_time, create_user, update_time, update_user) values (1, 'ADMIN', '管理员系统', '2021-03-13 16:46:28', 0, '2021-03-13 16:46:28', 0);
insert into mallUser.mall_role (role_id, role_name, role_info, create_time, create_user, update_time, update_user) values (2, 'BusinessAdmin', '业务管理员系统', '2021-03-13 16:46:52', 0, '2021-04-18 12:14:39', 0);
insert into mallUser.mall_role (role_id, role_name, role_info, create_time, create_user, update_time, update_user) values (3, 'MarketAdmin', '营销管理员系统', '2021-03-13 16:47:13', 0, '2021-04-18 12:14:39', 0);
insert into mallUser.mall_role (role_id, role_name, role_info, create_time, create_user, update_time, update_user) values (4, 'USER', '用户角色', '2021-03-13 16:47:38', 0, '2021-03-13 16:47:38', 0);
insert into mallUser.mall_role (role_id, role_name, role_info, create_time, create_user, update_time, update_user) values (5, 'TOURIST', '游客角色', '2021-03-13 16:47:54', 0, '2021-03-13 16:47:54', 0);

insert into mallUser.mall_role_interface (ui_id, role_id, inter_id, create_time, create_user, update_time, update_user) values (1, 1, 1, '2021-03-13 16:46:28', 0, '2021-03-13 16:46:28', 0);
insert into mallUser.mall_role_interface (ui_id, role_id, inter_id, create_time, create_user, update_time, update_user) values (2, 2, 2, '2021-03-13 16:46:52', 0, '2021-03-13 16:46:52', 0);
insert into mallUser.mall_role_interface (ui_id, role_id, inter_id, create_time, create_user, update_time, update_user) values (3, 3, 3, '2021-03-13 16:47:13', 0, '2021-03-13 16:47:13', 0);
insert into mallUser.mall_role_interface (ui_id, role_id, inter_id, create_time, create_user, update_time, update_user) values (4, 4, 4, '2021-03-13 16:47:38', 0, '2021-03-13 16:47:38', 0);
insert into mallUser.mall_role_interface (ui_id, role_id, inter_id, create_time, create_user, update_time, update_user) values (5, 5, 5, '2021-03-13 16:47:54', 0, '2021-03-13 16:47:54', 0);

insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (1, 'admin', '$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe', 1359003547, 0, 0, '2021-03-13 16:48:51');
insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (2, 'BusinessAdmin', '$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe', 1359003546, 0, 1, '2021-03-13 16:50:05');
insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (3, 'MarketAdmin', '$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe', 1359003546, 0, 0, '2021-03-13 16:50:15');
insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (4, 'user', '$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe', 1359003546, 0, 0, '2021-03-13 16:50:26');
insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (5, 'test', '$2a$10$8hBFFZoJaKjEfS0JIvdRh.KFaWdhIort5cb3JF5md1ltbSaK4vUDe', 1236599494, 1, 0, '2021-03-24 10:43:11');
insert into mallUser.mall_user (user_id, login_name, password_md5, phone_number, is_deleted, locked_flag, create_time) values (12, 'lirisheng', '$2a$10$j6W7KSVPZ.JpPnVDBJ4ogeyewnGrRhi6MTrnia.kME1vM/kMFw.a2', 13590035440, 0, 1, '2021-05-15 14:46:13');

insert into mallUser.mall_user_address (address_id, user_id, user_name, user_phone, default_flag, province_name, city_name, region_name, area_code, detail_address, is_deleted, create_time, update_time) values (1, 4, '黎日升', 13590035441, 0, '吉林省', '长春市', '南关区', 220102, '吉林省 长春市 南关区 大学路1号', 0, '2021-03-28 16:02:09', '2021-05-15 14:46:51');
insert into mallUser.mall_user_address (address_id, user_id, user_name, user_phone, default_flag, province_name, city_name, region_name, area_code, detail_address, is_deleted, create_time, update_time) values (4, 4, '黎日升', 13590035540, 1, '广东省', '湛江市', '赤坎区', 440802, '广东省湛江市赤坎区大学路1号', 0, '2021-05-14 16:22:20', '2021-05-15 14:46:51');

insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (1, 1, 1, '2021-03-13 16:48:51', 1, '2021-03-13 16:48:51', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (2, 2, 2, '2021-03-13 16:50:05', 2, '2021-03-13 16:50:05', 2);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (3, 3, 3, '2021-03-13 16:50:15', 3, '2021-03-13 16:50:15', 3);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (4, 4, 4, '2021-03-13 16:50:26', 4, '2021-03-13 16:50:26', 4);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (5, 7, 4, '2021-05-09 16:02:50', 1, '2021-05-09 16:02:50', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (6, 8, 4, '2021-05-09 16:40:53', 1, '2021-05-09 16:40:53', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (7, 9, 4, '2021-05-14 18:10:22', 1, '2021-05-14 18:10:22', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (8, 10, 4, '2021-05-14 18:26:13', 1, '2021-05-14 18:26:13', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (9, 11, 4, '2021-05-14 23:28:57', 1, '2021-05-14 23:28:57', 1);
insert into mallUser.mall_user_role (ur_id, user_id, role_id, create_time, create_user, update_time, update_user) values (10, 12, 4, '2021-05-15 14:46:14', 1, '2021-05-15 14:46:14', 1);

