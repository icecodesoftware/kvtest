// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lookup.proto

package com.github.icecodesoftware.generated;

public final class LookupProto {
  private LookupProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_lookup_Key_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_lookup_Key_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_lookup_Value_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_lookup_Value_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014lookup.proto\022\006lookup\"\022\n\003Key\022\013\n\003key\030\001 \001" +
      "(\t\"\026\n\005Value\022\r\n\005value\030\001 \001(\t28\n\rLookupServ" +
      "ice\022\'\n\003get\022\013.lookup.Key\032\r.lookup.Value\"\000" +
      "(\0010\001B5\n$com.github.icecodesoftware.gener" +
      "atedB\013LookupProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_lookup_Key_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_lookup_Key_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_lookup_Key_descriptor,
        new java.lang.String[] { "Key", });
    internal_static_lookup_Value_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_lookup_Value_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_lookup_Value_descriptor,
        new java.lang.String[] { "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}