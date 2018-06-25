SET @topLevelOrganisationId = 1;

INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT `description`, '' AS `department`, 0 AS `greenhouse`, @topLevelOrganisationId AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT DISTINCT `Ebene 1` AS `description` FROM bg_standorte) AS tmp_standorteList
;


INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT sl.`description`, '' AS `department`, 0 AS `greenhouse`, o.`id` AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT `Ebene 1` AS `parent_description`, `Ebene 15` AS `description` FROM bg_standorte
GROUP BY `Ebene 1`, `Ebene 15`) AS sl
LEFT JOIN `tbl_organisation` o ON o.description = sl.`parent_description`
;

INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT sl.`description`, '' AS `department`, 0 AS `greenhouse`, o.`id` AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT `Ebene 1` AS `parent_parent_description`, `Ebene 15` AS `parent_description`, `Ebene 2` AS `description` FROM bg_standorte
GROUP BY `Ebene 1`, `Ebene 15`, `Ebene 2`) AS sl
LEFT JOIN `tbl_organisation` o ON o.description = sl.`parent_description`
LEFT JOIN `tbl_organisation` o2 ON o2.description = sl.`parent_parent_description` AND o2.id = o.parent_organisation_id
WHERE o2.id IS NOT NULL
;

INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT sl.`description`, '' AS `department`, 0 AS `greenhouse`, o.`id` AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT `Ebene 1` AS `parent_parent_parent_description`, `Ebene 15` AS `parent_parent_description`, `Ebene 2` AS `parent_description`, `Ebene 3` AS `description` FROM bg_standorte
GROUP BY `Ebene 1`, `Ebene 15`, `Ebene 2`, `Ebene 3`) AS sl
LEFT JOIN `tbl_organisation` o ON o.description = sl.`parent_description`
LEFT JOIN `tbl_organisation` o2 ON o2.description = sl.`parent_parent_description` AND o2.id = o.parent_organisation_id
LEFT JOIN `tbl_organisation` o3 ON o3.description = sl.`parent_parent_parent_description` AND o3.id = o2.parent_organisation_id
WHERE o2.id IS NOT NULL
AND o3.id IS NOT NULL
;

INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT sl.`description`, '' AS `department`, 0 AS `greenhouse`, o.`id` AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT `Ebene 1` AS `parent_parent_parent_parent_description`, `Ebene 15` AS `parent_parent_parent_description`, `Ebene 2` AS `parent_parent_description`, `Ebene 3` AS `parent_description`, `Ebene 4` AS `description` FROM bg_standorte
WHERE `Ebene 4` <> ''
GROUP BY `Ebene 1`, `Ebene 15`, `Ebene 2`, `Ebene 3`, `Ebene 4`) AS sl
LEFT JOIN `tbl_organisation` o ON o.description = sl.`parent_description`
LEFT JOIN `tbl_organisation` o2 ON o2.description = sl.`parent_parent_description` AND o2.id = o.parent_organisation_id
LEFT JOIN `tbl_organisation` o3 ON o3.description = sl.`parent_parent_parent_description` AND o3.id = o2.parent_organisation_id
LEFT JOIN `tbl_organisation` o4 ON o4.description = sl.`parent_parent_parent_parent_description` AND o4.id = o3.parent_organisation_id
WHERE o2.id IS NOT NULL
AND o3.id IS NOT NULL
AND o4.id IS NOT NULL
;

INSERT INTO `tbl_organisation` (`description`, `department`, `greenhouse`, `parent_organisation_id`, `gardener_id`, `ipen_code`, `index_seminum_start`)
SELECT sl.`description`, '' AS `department`, 0 AS `greenhouse`, o.`id` AS `parent_organisation_id`, 1 AS `gardener_id`, NULL AS `ipen_code`, 0 AS `index_seminum_start` FROM
(SELECT
	`Ebene 1` AS `parent_parent_parent_parent_parent_description`,
	`Ebene 15` AS `parent_parent_parent_parent_description`,
	`Ebene 2` AS `parent_parent_parent_description`,
	`Ebene 3` AS `parent_parent_description`,
	`Ebene 4` AS `parent_description` ,
	`Ebene 5` AS `description`
FROM bg_standorte
WHERE `Ebene 5` <> ''
GROUP BY `Ebene 1`, `Ebene 15`, `Ebene 2`, `Ebene 3`, `Ebene 4`, `Ebene 5`) AS sl
LEFT JOIN `tbl_organisation` o ON o.description = sl.`parent_description`
LEFT JOIN `tbl_organisation` o2 ON o2.description = sl.`parent_parent_description` AND o2.id = o.parent_organisation_id
LEFT JOIN `tbl_organisation` o3 ON o3.description = sl.`parent_parent_parent_description` AND o3.id = o2.parent_organisation_id
LEFT JOIN `tbl_organisation` o4 ON o4.description = sl.`parent_parent_parent_parent_description` AND o4.id = o3.parent_organisation_id
LEFT JOIN `tbl_organisation` o5 ON o5.description = sl.`parent_parent_parent_parent_parent_description` AND o5.id = o4.parent_organisation_id
WHERE o2.id IS NOT NULL
AND o3.id IS NOT NULL
AND o4.id IS NOT NULL
AND o5.id IS NOT NULL
;
