create table GB_Entry (
	EntryId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null,
	message VARCHAR(75) null
);