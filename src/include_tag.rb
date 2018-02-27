require "liquid"

class Include < Liquid::Tag
  def initialize(tag_name, args, options)
    super
    @file = args.strip
  end

  def render(context)
    File.read "src/views/#{@file}"
  end

end

Liquid::Template.register_tag("include", Include)
