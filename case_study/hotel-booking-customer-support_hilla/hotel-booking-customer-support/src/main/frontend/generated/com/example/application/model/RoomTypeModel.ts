import { _enum as _enum_1, EnumModel as EnumModel_1, makeEnumEmptyValueCreator as makeEnumEmptyValueCreator_1 } from "@vaadin/hilla-lit-form";
import RoomType_1 from "./RoomType.js";
class RoomTypeModel extends EnumModel_1<typeof RoomType_1> {
    static override createEmptyValue = makeEnumEmptyValueCreator_1(RoomTypeModel);
    readonly [_enum_1] = RoomType_1;
}
export default RoomTypeModel;
